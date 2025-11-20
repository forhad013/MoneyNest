package com.greenrobotdev.linklibrary.screens.root

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.greenrobotdev.linklibrary.screens.add.AddLinkScreen
import com.greenrobotdev.linklibrary.screens.details.LinkDetailScreen
import com.greenrobotdev.linklibrary.screens.home.HomeScreen
import io.github.xxfast.decompose.router.stack.RoutedContent
import io.github.xxfast.decompose.router.stack.Router
import io.github.xxfast.decompose.router.stack.rememberRouter

@Composable
fun RootScreen() {
    val router: Router<RootScreens> = rememberRouter { listOf(RootScreens.Home) }

    RoutedContent(
        router = router,
    ) { screen ->
        when (screen) {
            is RootScreens.Home -> HomeScreen(
                onNavigateToDetail = { linkId ->
                    router.push(RootScreens.LinkDetail(linkId)) },
                onAddLink = { initialUrl -> router.push(RootScreens.AddLink(initialUrl)) }
            )

            is RootScreens.LinkDetail -> LinkDetailScreen(
                linkId = screen.linkId,
                onBack = { router.pop() }
            )

            is RootScreens.AddLink -> AddLinkScreen(
                initialUrl = screen.initialUrl,
                onBack = { router.pop() },
            )
        }
    }
}
