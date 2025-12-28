package com.mustafa.products.data.repository

import com.mustafa.products.data.data_source.ProductApiService
import com.mustafa.products.data.response.ProductResponseDto
import com.mustafa.products.data.response.RatingResponseDto
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ProductRepositoryImplTest {

    private lateinit var apiService: ProductApiService
    private lateinit var repository: ProductRepositoryImpl

    @Before
    fun setup() {
        apiService = mockk()
        repository = ProductRepositoryImpl(apiService)
    }

    @Test
    fun `getProducts returns success with products`() = runTest {
        // Given
        val mockProducts = listOf(
            ProductResponseDto(
                id = 1,
                title = "Test Product",
                price = 10.0,
                description = "Test Description",
                category = "test",
                image = "https://test.com/image.jpg",
                rating = RatingResponseDto(rate = 4.5, count = 100)
            )
        )
        coEvery { apiService.getProducts() } returns mockProducts

        // When
        val result = repository.getProducts()

        // Then
        assertTrue(result.isSuccess)
        assertTrue(result.getOrNull()?.isNotEmpty() == true)
    }

    @Test
    fun `getProducts returns failure on exception`() = runTest {
        // Given
        coEvery { apiService.getProducts() } throws Exception("Network error")

        // When
        val result = repository.getProducts()

        // Then
        assertTrue(result.isFailure)
    }
}


