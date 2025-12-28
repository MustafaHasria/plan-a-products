package com.mustafa.products.data.mapper

import com.mustafa.products.data.response.ProductResponseDto
import com.mustafa.products.data.response.RatingResponseDto
import com.mustafa.products.domain.model.Product
import com.mustafa.products.domain.model.Rating

fun RatingResponseDto.toDomain(): Rating {
    return Rating(
        rate = rate,
        count = count
    )
}

fun ProductResponseDto.toDomain(): Product {
    return Product(
        id = id,
        title = title,
        price = price,
        description = description,
        category = category,
        imageUrl = image,
        rating = rating.toDomain()
    )
}

fun List<ProductResponseDto>.toDomain(): List<Product> {
    return map { it.toDomain() }
}


