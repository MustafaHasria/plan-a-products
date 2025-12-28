package com.mustafa.products.core.rout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    // Nested navigation graph for products (List + Detail)
    // ViewModel is scoped to this graph, shared between List and Detail screens
    ProductsNavGraph(
        navController = navController,
        modifier = modifier
    )
}
