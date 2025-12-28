package com.mustafa.products.core.rout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mustafa.products.presentation.screens.ProductDetailScreen
import com.mustafa.products.presentation.screens.ProductsScreen
import com.mustafa.products.presentation.viewmodel.ProductsViewModel

@Composable
fun ProductsNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: ProductsViewModel = hiltViewModel()
) {
    NavHost(
        navController = navController,
        startDestination = Routes.ProductsList.route,
        modifier = modifier
    ) {
        composable(Routes.ProductsList.route) {
            ProductsScreen(
                onProductClick = { product ->
                    viewModel.selectProduct(product)
                    navController.navigate(Routes.ProductDetail.route)
                },
                viewModel = viewModel
            )
        }
        
        composable(Routes.ProductDetail.route) {
            ProductDetailScreen(
                onClose = { 
                    viewModel.clearSelectedProduct()
                    navController.popBackStack() 
                },
                viewModel = viewModel
            )
        }
    }
}

