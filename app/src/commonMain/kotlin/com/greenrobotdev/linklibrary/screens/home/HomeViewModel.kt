package com.greenrobotdev.linklibrary.screens.home

import app.cash.molecule.RecompositionMode
import app.cash.molecule.moleculeFlow
import com.greenrobotdev.linklibrary.domain.repository.LinkRepository
import com.greenrobotdev.linklibrary.utils.ViewModel
import io.github.xxfast.decompose.router.RouterContext
import io.github.xxfast.decompose.router.state
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeViewModel(
    context: RouterContext
) : ViewModel(), KoinComponent {
    private val initialState: HomeState = context.state(HomeState()) { states.value }

    private val eventsFlow: MutableSharedFlow<HomeEvent> = MutableSharedFlow(5)
    private val linkRepository: LinkRepository by inject()

    val states by lazy {
        moleculeFlow(RecompositionMode.Immediate) {
            HomeUseCase(initialState, eventsFlow, linkRepository)
        }.stateIn(this, SharingStarted.Lazily, initialState)
    }

    fun onRefresh() { launch { eventsFlow.emit(HomeEvent.Refresh) } }
    fun onToggleFavorite(linkId: String) { launch { eventsFlow.emit(HomeEvent.ToggleFavorite(linkId)) } }
}
