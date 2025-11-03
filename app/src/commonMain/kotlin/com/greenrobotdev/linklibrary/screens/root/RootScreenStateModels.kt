package com.greenrobotdev.linklibrary.screens.root

import kotlinx.serialization.Serializable

@Serializable
sealed class RootScreen {
    @Serializable
    data object Home : RootScreen()

    @Serializable
    data class LinkDetail(val linkId: String) : RootScreen()

    @Serializable
    data class AddLink(val initialUrl: String? = null) : RootScreen()
}