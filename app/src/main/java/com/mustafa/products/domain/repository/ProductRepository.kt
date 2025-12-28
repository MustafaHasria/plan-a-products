package com.mustafa.products.domain.repository

import com.mustafa.products.domain.model.Product

interface ProductRepository {
    suspend fun getProducts(): Result<List<Product>>
}


