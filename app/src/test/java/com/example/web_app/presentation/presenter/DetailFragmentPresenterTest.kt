package com.example.web_app.presentation.presenter

import com.example.web_app.domain.entity.Brawler
import com.example.web_app.domain.usecase.GetBrawlerByIdUseCase
import com.example.web_app.presentation.view.`DetailFragmentView$$State`
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers

import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class DetailFragmentPresenterTest {
    @MockK
    lateinit var brawlerIdUseCase: GetBrawlerByIdUseCase

    @MockK(relaxUnitFun = true)
    lateinit var viewState: `DetailFragmentView$$State`

    lateinit var presenter: DetailFragmentPresenter

    @BeforeEach
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { Schedulers.trampoline() }

        presenter = spyk(
            DetailFragmentPresenter(brawlerIdUseCase)
        )
        presenter.setViewState(viewState)
    }

    @Test
    fun onLoadDetailInfo() {
        val expectedBrawler = "SHELLY"
        val expectedId = 16000000
        val mockBrawler = mockk<Brawler>() {
            every { name } returns expectedBrawler
        }
        every { brawlerIdUseCase.invoke(expectedId) } returns Single.just(mockBrawler)
        presenter.onLoadDetailInfo(expectedId)
        verifyOrder {
            viewState.showLoading()
            viewState.hideLoading()
        }
        verify {
            viewState.loadBrawlerInfo(mockBrawler)
        }
    }

    @Test
    fun onShowError() {
        // arrange
        val expectedId = 16000000
        val mockError = mockk<Throwable>()
        every { brawlerIdUseCase.invoke(expectedId) } returns Single.error(mockError)
        // act
        presenter.onLoadDetailInfo(expectedId)
        // assert
        verifyOrder {
            viewState.showLoading()
            viewState.hideLoading()
        }
        verify(inverse = true) {
            viewState.loadBrawlerInfo(any())
        }
        verify {
            viewState.showError(mockError)
        }
    }
}