package com.mustafa.products.core.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * A reusable pull-to-refresh component that wraps content with swipe refresh functionality.
 * Uses the Material Compose pull refresh API (Modifier.pullRefresh) which is built on top of
 * androidx.swiperefreshlayout:swiperefreshlayout.
 * 
 * Features:
 * - Pull down at the top of the list to refresh data
 * - Shows a Material Design loading indicator during refresh
 * - Automatically manages grid state for optimal scrolling
 * - Built on the stable AndroidX SwipeRefreshLayout component
 * 
 * @param isRefreshing Whether the refresh operation is currently in progress
 * @param onRefresh Callback invoked when the user triggers a refresh by swiping down
 * @param modifier Optional modifier for the container
 * @param content The content to be displayed, receives the grid state for synchronization
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeRefresh(
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (LazyGridState) -> Unit
) {
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = onRefresh
    )
    val gridState = rememberLazyGridState()
    
    Box(
        modifier = modifier.pullRefresh(pullRefreshState)
    ) {
        // Content (the scrollable grid)
        content(gridState)
        
        // Pull-to-refresh indicator
        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}
