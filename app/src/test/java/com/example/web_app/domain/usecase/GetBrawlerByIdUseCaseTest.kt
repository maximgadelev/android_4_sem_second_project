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
internal class GetBrawlerByIdUseCaseTest {
    @MockK
    lateinit var repository: BrawlerRepository

    lateinit var useCase: GetBrawlerByIdUseCase

    @BeforeEach
    fun setUp() {
        useCase = GetBrawlerByIdUseCase(
            repository
        )
    }

    @Test
    operator fun invoke() {
        //arrange
        val expectedValue = 16000009
        val expectedBrawlerName = "DYNAMIKE"
        val mockBrawler = mockk<Brawler>() {
            every { name } returns expectedBrawlerName
        }
        //act
        every {
            repository.getBrawlerById(expectedValue)
        } returns Single.just(mockBrawler)
        //assert
        val result = useCase(expectedValue)
        assertEquals(
            expectedBrawlerName,
            result.blockingGet().name
        )
    }

    @Test
    @DisplayName("Then request brawler, we awaiting error")
    fun invokeTestException() {
        // arrange
        val expectedValue = 160055555
        val mockError = mockk<Throwable>()
        every {
            repository.getBrawlerById(expectedValue)
        } returns Single.error(mockError)

        // act
        val result = useCase(expectedValue)

        // assert
        assertEquals(
            mockError,
            result.blockingGet()
        )
    }
}