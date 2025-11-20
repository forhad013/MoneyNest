package com.greenrobotdev.linklibrary.screens.root

import kotlinx.serialization.Serializable

@Serializable
sealed class RootScreens {
    @Serializable
    data object Home : RootScreens()

    @Serializable
    data class LinkDetail(val linkId: String) : RootScreens()

    @Serializable
    data class AddLink(val initialUrl: String? = null) : RootScreens()
}