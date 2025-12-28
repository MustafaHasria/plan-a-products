package com.mustafa.products.data.repository

import com.mustafa.products.data.data_source.ProductApiService
import com.mustafa.products.data.mapper.toDomain
import com.mustafa.products.domain.model.Product
import com.mustafa.products.domain.repository.ProductRepository
import java.io.IOException
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val apiService: ProductApiService
) : ProductRepository {

    override suspend fun getProducts(): Result<List<Product>> {
        return try {
            val response = apiService.getProducts()
            Result.success(response.toDomain())
        } catch (e: IOException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}


