package com.greenrobotdev.linklibrary.screens.root

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.greenrobotdev.linklibrary.screens.home.RootScreen
import com.greenrobotdev.linklibrary.screens.home.HomeViewModel
import io.github.xxfast.decompose.router.rememberOnRoute
import io.github.xxfast.decompose.router.stack.RoutedContent
import io.github.xxfast.decompose.router.stack.Router
import io.github.xxfast.decompose.router.stack.rememberRouter

@Composable
fun RootScreen(
    onNavigateToDetail: (String) -> Unit = {},
    onAddLink: (String?) -> Unit = {}
) {
    val router: Router<RootScreen> = rememberRouter { listOf(RootScreen.Home) }

    val viewModel: HomeViewModel = rememberOnRoute() { HomeViewModel(this) }

    val state by viewModel.states.collectAsState()
    val scope = rememberCoroutineScope()

    RoutedContent(
        router = router,
    ) { screen ->
        when (screen) {
            is RootScreen.Home -> HomeContent(
                state = state,
                onRefresh = { viewModel.onRefresh() },
                onToggleFavorite = { linkId ->
                    viewModel.onToggleFavorite(linkId)
                },
                onLinkClick = { linkId ->
                    router.push(RootScreen.LinkDetail(linkId))
                    onNavigateToDetail(linkId)
                },
                onAddLink = { initialUrl ->
                    router.push(RootScreen.AddLink(initialUrl))
                    onAddLink(initialUrl)
                }
            )

            is RootScreen.LinkDetail -> LinkDetailScreen(
                linkId = screen.linkId,
                onBack = { router.pop() }
            )

            is RootScreen.AddLink -> AddLinkScreen(
                initialUrl = screen.initialUrl,
                onBack = { router.pop() },
                onLinkAdded = {
                    viewModel.onRefresh()
                    router.pop()
                }
            )
        }
    }
}
