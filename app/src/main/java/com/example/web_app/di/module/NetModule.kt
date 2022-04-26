package com.example.web_app.di.module

import androidx.viewbinding.BuildConfig
import com.example.web_app.data.api.Api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

private const val BASE_URL = "https://api.brawlstars.com/v1/"
private const val TOKEN ="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6ImJhZDE3NGIzLTI5ZDAtNDA3YS05MjRmLTI3Y2UwZTJjZTgzZiIsImlhdCI6MTY1MDk4MTAwNSwic3ViIjoiZGV2ZWxvcGVyLzcxYzNiZDI1LTc4MjEtZmFmYy1kMzNkLTkyZDMxZjM5ZmJhNyIsInNjb3BlcyI6WyJicmF3bHN0YXJzIl0sImxpbWl0cyI6W3sidGllciI6ImRldmVsb3Blci9zaWx2ZXIiLCJ0eXBlIjoidGhyb3R0bGluZyJ9LHsiY2lkcnMiOlsiMTc2LjUyLjEwMi4yIl0sInR5cGUiOiJjbGllbnQifV19.2M5eaWfmrNDWXFfBml194V2g3QZfHugfRaq015TTVCt9PdVXFIADesHFwj92lzaMSMtePebFDx4e0P8Jyzhj6w"
@Module
@InstallIn(SingletonComponent::class)
class NetModule {
    @Provides
    @Named("token")
    fun tokenInterceptor(): Interceptor = Interceptor {
        val requestBuilder = it.request().newBuilder()
        requestBuilder.addHeader("Authorization", "Bearer $TOKEN")
        it.proceed(requestBuilder.build())
    }

    @Provides
    @Named("logger")
    fun provideLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor()
            .setLevel(
                HttpLoggingInterceptor.Level.BODY
            )
    }

    @Provides
    fun okhttp(
        @Named("token") tokenInterceptor: Interceptor,
        @Named("logger") loggingInterceptor: Interceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(tokenInterceptor)
            .also {
                if (BuildConfig.DEBUG) {
                    it.addInterceptor(loggingInterceptor)
                }
            }
            .build()

    @Provides
    fun provideGsonConverter(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    fun providesApi(
        okHttpClient: OkHttpClient,
        gsonConverter: GsonConverterFactory,
    ): Api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(gsonConverter)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(Api::class.java)
}