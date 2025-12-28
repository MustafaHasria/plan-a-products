package com.mustafa.products.domain.use_case

import com.mustafa.products.domain.model.Product
import com.mustafa.products.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(): Result<List<Product>> {
        return repository.getProducts()
    }
}


