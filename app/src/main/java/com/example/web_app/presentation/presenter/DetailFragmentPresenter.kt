package com.example.web_app.presentation.presenter

import com.example.web_app.domain.entity.Brawler
import com.example.web_app.domain.usecase.GetBrawlerByIdUseCase
import com.example.web_app.presentation.view.DetailFragmentView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.MvpPresenter
import javax.inject.Inject

class DetailFragmentPresenter @Inject constructor(
    private val getBrawlerByIdUseCase: GetBrawlerByIdUseCase
) : MvpPresenter<DetailFragmentView>() {
    private val disposables = CompositeDisposable()
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

    }

    override fun attachView(view: DetailFragmentView?) {
        super.attachView(view)

    }

    override fun detachView(view: DetailFragmentView?) {
        super.detachView(view)
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }

    fun onLoadDetailInfo(id:Int) {
        disposables += getBrawlerByIdUseCase(id)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                viewState.showLoading()
            }
            .doAfterTerminate {
                viewState.hideLoading()
            }
            .subscribeBy(
                onSuccess = {
                    viewState.loadBrawlerInfo(it)
                }, onError = { error ->
                    viewState.showError(error)
                })
                }
    }
