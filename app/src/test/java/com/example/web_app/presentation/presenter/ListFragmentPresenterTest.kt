package com.example.web_app.presentation.presenter

import com.example.web_app.domain.entity.Brawler
import com.example.web_app.domain.usecase.GetBrawlersUseCase
import com.example.web_app.presentation.view.`DetailFragmentView$$State`
import com.example.web_app.presentation.view.`ListFragmentView$$State`
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class ListFragmentPresenterTest {
    @MockK
    lateinit var brawlersUseCase: GetBrawlersUseCase

    @MockK(relaxUnitFun = true)
    lateinit var viewState: `ListFragmentView$$State`

    lateinit var presenter: ListFragmentPresenter

    @BeforeEach
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { Schedulers.trampoline() }

        presenter = spyk(
            ListFragmentPresenter(brawlersUseCase)
        )
        presenter.setViewState(viewState)
    }

    @Test
    fun onLoadList() {
        //arrange
        val mockList = arrayListOf<Brawler>(
            mockk { every { id } returns 16000000 },
            mockk { every { id } returns 16000009 },
            mockk { every { id } returns 16000001 },
            mockk { every { id } returns 16000002 },
            mockk { every { id } returns 16000003 },
            mockk { every { id } returns 16000004 },
            mockk { every { id } returns 16000005 },
            mockk { every { id } returns 16000006 },
        )
        every { brawlersUseCase.invoke() } returns Single.just(mockList)
        //act
        presenter.onLoadList()
        //asert
        verifyOrder {
            viewState.showLoading()
            viewState.hideLoading()
        }
        verify {
            viewState.showList(mockList)
        }
    }
    @Test
    fun onShowError(){
        //arrange
        val mockError = mockk<Throwable>()
        every { brawlersUseCase.invoke() } returns Single.error(mockError)
        //act
        presenter.onLoadList()
        //assert
        verifyOrder {
            viewState.showLoading()
            viewState.hideLoading()
        }
        verify {
            viewState.showError(mockError)
        }

    }
}