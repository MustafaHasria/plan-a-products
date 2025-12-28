package com.mustafa.products.core.network

import okhttp3.logging.HttpLoggingInterceptor

object NetworkInterceptor {
    fun createLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}


