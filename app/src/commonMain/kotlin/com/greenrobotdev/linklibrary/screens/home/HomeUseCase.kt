package com.greenrobotdev.linklibrary.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.greenrobotdev.linklibrary.domain.repository.LinkRepository
import kotlinx.coroutines.flow.Flow

@Composable
fun HomeUseCase(
    initialState: HomeState,
    events: Flow<HomeEvent>,
    linkRepository: LinkRepository
): HomeState {

    var links by remember { mutableStateOf(initialState.links) }
    var isLoading by remember { mutableStateOf(initialState.isLoading) }
    var error by remember { mutableStateOf(initialState.error) }

    LaunchedEffect(Unit) {
        isLoading = true
        linkRepository.getLinks().collect { result ->
            isLoading = false
//            result.doIfSuccess { links = it }
            result.onFailure { error = it.message }
        }
    }

    LaunchedEffect(Unit) {
        events.collect { event ->
            when (event) {
                is HomeEvent.Refresh -> {
                    isLoading = true
                    linkRepository.getLinks().collect { result ->
                        isLoading = false

                        result.onSuccess{ links = it }
                        result.onFailure { error = it.message }
                    }
                }
                is HomeEvent.ToggleFavorite -> {
                    linkRepository.toggleFavorite(event.id).collect { result ->
                        result.onSuccess { updatedLink ->
                            links = links?.map { link ->
                                if (link.id == updatedLink.id) updatedLink else link
                            }
                        }
                        result.onFailure { error = it.message }
                    }
                }
            }
        }
    }
    
    return HomeState(
        isLoading = isLoading,
        error = error,
        links = links
    )
}
