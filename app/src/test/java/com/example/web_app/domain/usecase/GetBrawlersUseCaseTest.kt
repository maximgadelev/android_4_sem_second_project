package com.example.web_app.domain.usecase

import com.example.web_app.domain.entity.Brawler
import com.example.web_app.domain.repository.BrawlerRepository
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class GetBrawlersUseCaseTest {
    @MockK
    lateinit var repository: BrawlerRepository

    lateinit var useCase: GetBrawlersUseCase

    @BeforeEach
    fun setUp() {
        useCase = GetBrawlersUseCase(
            repository
        )
    }
    @Test
    operator fun invoke() {
        //arrange
        val expectedList= arrayListOf<Brawler>(
            mockk { every { id } returns 16000000 },
            mockk { every { id } returns 16000009 },
            )
        //act
        every {
            repository.getBrawlers()
        }returns Single.just(expectedList)
        //assert
        val result = useCase.invoke()
        assertEquals(
           1600000,
            result.blockingGet()[0].id
        )
    }
    @Test
    @DisplayName("Then request brawlers, we awaiting error")
    fun invokeTestException() {
        // arrange
        val mockError = mockk<Throwable>()
        every {
            repository.getBrawlers()
        } returns Single.error(mockError)

        // act
        val result = useCase.invoke()

        // assert
        assertEquals(
            mockError,
            result.blockingGet()
        )
    }
}