package com.example.web_app.presentation.presenter

import com.example.web_app.domain.usecase.GetBrawlersUseCase
import com.example.web_app.presentation.view.ListFragmentView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.MvpPresenter
import javax.inject.Inject

class ListFragmentPresenter @Inject constructor(
    private val getBrawlersUseCase: GetBrawlersUseCase,
) : MvpPresenter<ListFragmentView>() {
    private val disposables = CompositeDisposable()
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

    }

    override fun attachView(view: ListFragmentView?) {
        super.attachView(view)

    }

    override fun detachView(view: ListFragmentView?) {
        super.detachView(view)
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }

    fun onLoadList() {
        disposables += getBrawlersUseCase.invoke()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                viewState.showLoading()
            }.doAfterTerminate {
                viewState.hideLoading()
            }
            .subscribeBy(onSuccess = {
                viewState.showList(it.slice(0..8))
            }, onError = { error ->
                viewState.showError(error)
            })
    }
}