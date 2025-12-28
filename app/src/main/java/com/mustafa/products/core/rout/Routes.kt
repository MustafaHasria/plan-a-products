package com.mustafa.products.core.rout

sealed class Routes(val route: String) {
    object ProductsList : Routes("products_list")
    object ProductDetail : Routes("product_detail")
}


