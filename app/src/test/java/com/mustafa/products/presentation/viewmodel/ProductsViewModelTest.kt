package com.mustafa.products.presentation.viewmodel

import com.mustafa.products.domain.model.Product
import com.mustafa.products.domain.model.Rating
import com.mustafa.products.domain.use_case.GetProductsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ProductsViewModelTest {

    private lateinit var getProductsUseCase: GetProductsUseCase
    private lateinit var viewModel: ProductsViewModel

    @Before
    fun setup() {
        getProductsUseCase = mockk()
    }

    @Test
    fun `loadProducts updates state with products`() = runTest {
        // Given
        val mockProducts = listOf(
            Product(
                id = 1,
                title = "Test Product",
                price = 10.0,
                description = "Test Description",
                category = "electronics",
                imageUrl = "https://test.com/image.jpg",
                rating = Rating(rate = 4.5, count = 100)
            )
        )
        coEvery { getProductsUseCase() } returns Result.success(mockProducts)
        viewModel = ProductsViewModel(getProductsUseCase)

        // When
        advanceUntilIdle()

        // Then
        val state = viewModel.uiState.value
        assertFalse(state.isLoading)
        assertTrue(state.products.isNotEmpty())
        assertEquals(1, state.products.size)
        assertTrue(state.productsByCategory.containsKey("electronics"))
    }

    @Test
    fun `loadProducts handles error`() = runTest {
        // Given
        coEvery { getProductsUseCase() } returns Result.failure(Exception("Network error"))
        viewModel = ProductsViewModel(getProductsUseCase)

        // When
        advanceUntilIdle()

        // Then
        val state = viewModel.uiState.value
        assertFalse(state.isLoading)
        assertTrue(state.error != null)
    }
}

