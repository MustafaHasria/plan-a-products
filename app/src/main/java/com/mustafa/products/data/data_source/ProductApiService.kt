package com.mustafa.products.data.data_source

import com.mustafa.products.data.response.ProductResponseDto
import retrofit2.http.GET

interface ProductApiService {
    @GET("products")
    suspend fun getProducts(): List<ProductResponseDto>
}


