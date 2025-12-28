package com.mustafa.products.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafa.products.domain.model.Product
import com.mustafa.products.domain.use_case.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProductsUiState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val productsByCategory: Map<String, List<Product>> = emptyMap(),
    val categories: List<String> = emptyList(),
    val selectedCategory: String? = null,
    val filteredProducts: List<Product> = emptyList(),
    val selectedProduct: Product? = null,
    val error: String? = null
)

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductsUiState())
    val uiState: StateFlow<ProductsUiState> = _uiState.asStateFlow()

    init {
        loadProducts()
    }

    fun loadProducts() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            
            getProductsUseCase().fold(
                onSuccess = { products ->
                    // Products are already in API order - preserve it
                    val productsByCategory = products.groupBy { it.category }
                    val categories = productsByCategory.keys.sorted()
                    val selectedCategory = _uiState.value.selectedCategory
                    val filteredProducts = if (selectedCategory == null) {
                        // Show all products in original API order
                        products
                    } else {
                        // Filter products by selected category
                        products.filter { it.category == selectedCategory }
                    }
                    
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        products = products, // Original API order preserved
                        productsByCategory = productsByCategory,
                        categories = categories,
                        filteredProducts = filteredProducts,
                        error = null
                    )
                },
                onFailure = { throwable ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = throwable.message ?: "An error occurred"
                    )
                }
            )
        }
    }

    fun selectCategory(category: String?) {
        val filteredProducts = if (category == null) {
            // Show all products in original API order
            _uiState.value.products
        } else {
            // Filter products by selected category
            _uiState.value.products.filter { it.category == category }
        }
        
        _uiState.value = _uiState.value.copy(
            selectedCategory = category,
            filteredProducts = filteredProducts
        )
    }

    fun refreshProducts() {
        loadProducts()
    }

    fun selectProduct(product: Product) {
        _uiState.value = _uiState.value.copy(selectedProduct = product)
    }

    fun clearSelectedProduct() {
        _uiState.value = _uiState.value.copy(selectedProduct = null)
    }
}

