package com.greenrobotdev.linklibrary.screens.add

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

class AddLinkViewModel(
    context: RouterContext,
    private val initialUrl: String? = null
) : ViewModel(), KoinComponent {
    
    private val initialState: AddLinkState = context.state(AddLinkState()) { states.value }
    
    private val eventsFlow: MutableSharedFlow<AddLinkEvent> = MutableSharedFlow(10)
    private val linkRepository: LinkRepository by inject()

    val states by lazy {
        moleculeFlow(RecompositionMode.Immediate) {
            AddLinkUseCase(initialState, eventsFlow, linkRepository, initialUrl)
        }.stateIn(this, SharingStarted.Lazily, initialState)
    }

    fun onEvent(event: AddLinkEvent) {
        launch { eventsFlow.emit(event) }
    }
}
