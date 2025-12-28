package com.mustafa.products.domain.use_case

import com.mustafa.products.domain.model.Product
import com.mustafa.products.domain.model.Rating
import com.mustafa.products.domain.repository.ProductRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetProductsUseCaseTest {

    private lateinit var repository: ProductRepository
    private lateinit var useCase: GetProductsUseCase

    @Before
    fun setup() {
        repository = mockk()
        useCase = GetProductsUseCase(repository)
    }

    @Test
    fun `invoke returns success with products`() = runTest {
        // Given
        val mockProducts = listOf(
            Product(
                id = 1,
                title = "Test Product",
                price = 10.0,
                description = "Test Description",
                category = "test",
                imageUrl = "https://test.com/image.jpg",
                rating = Rating(rate = 4.5, count = 100)
            )
        )
        coEvery { repository.getProducts() } returns Result.success(mockProducts)

        // When
        val result = useCase()

        // Then
        assertTrue(result.isSuccess)
        assertTrue(result.getOrNull()?.isNotEmpty() == true)
    }

    @Test
    fun `invoke returns failure on repository error`() = runTest {
        // Given
        coEvery { repository.getProducts() } returns Result.failure(Exception("Error"))

        // When
        val result = useCase()

        // Then
        assertTrue(result.isFailure)
    }
}


