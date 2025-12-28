package com.mustafa.products.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mustafa.products.core.component.CategoryFilter
import com.mustafa.products.core.component.ErrorMessage
import com.mustafa.products.core.component.LoadingIndicator
import com.mustafa.products.core.component.SwipeRefresh
import com.mustafa.products.core.rout.Routes
import com.mustafa.products.domain.model.Product
import com.mustafa.products.presentation.components.ProductItem
import com.mustafa.products.presentation.viewmodel.ProductsViewModel

@Composable
fun ProductsScreen(
    onProductClick: (Product) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProductsViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(modifier = modifier.fillMaxSize()) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when {
                uiState.isLoading && uiState.products.isEmpty() -> {
                    LoadingIndicator()
                }
                uiState.error != null && uiState.products.isEmpty() -> {
                    ErrorMessage(
                        message = uiState.error ?: "An error occurred",
                        onRetry = { viewModel.refreshProducts() }
                    )
                }
                else -> {
                    SwipeRefresh(
                        isRefreshing = uiState.isLoading,
                        onRefresh = { viewModel.refreshProducts() }
                    ) { gridState ->
                        Column(modifier = Modifier.fillMaxSize()) {
                            CategoryFilter(
                                categories = uiState.categories,
                                selectedCategory = uiState.selectedCategory,
                                onCategorySelected = { category ->
                                    viewModel.selectCategory(category)
                                }
                            )
                            
                            LazyVerticalGrid(
                                state = gridState,
                                columns = GridCells.Fixed(2),
                                modifier = Modifier.fillMaxSize(),
                                contentPadding = PaddingValues(8.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                items(
                                    items = uiState.filteredProducts,
                                    key = { it.id }
                                ) { product ->
                                    ProductItem(
                                        product = product,
                                        onClick = { onProductClick(product) },
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

