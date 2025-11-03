package com.greenrobotdev.linklibrary.data.repository

import com.greenrobotdev.linklibrary.domain.repository.LinkRepository
import com.greenrobotdev.linklibrary.model.Link
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class LinkRepositoryImpl : LinkRepository {
    
    private val _links = MutableStateFlow(Link.sampleLinks)
    
    override suspend fun getLinks(): Flow<Result<List<Link>>> {
        return _links.map { links ->
            Result.success(links)
        }
    }
    
    override suspend fun toggleFavorite(linkId: String): Flow<Result<Link>> {
        return _links.map { links ->
            val updatedLinks = links.map { link ->
                if (link.id == linkId) {
                    link.copy(isFavorite = !link.isFavorite)
                } else {
                    link
                }
            }
            _links.value = updatedLinks
            val updatedLink = updatedLinks.find { it.id == linkId }
            updatedLink?.let { Result.success(it) } ?: Result.failure(Exception("Link not found"))
        }
    }
    
    @OptIn(ExperimentalUuidApi::class)
    override suspend fun addLink(link: Link): Flow<Result<Link>> {
        return _links.map { links ->
            val newLink = link.copy(id = Uuid.random().toString())
            val updatedLinks = links + newLink
            _links.value = updatedLinks
            Result.success(newLink)
        }
    }
    
    override suspend fun deleteLink(linkId: String): Flow<Result<Unit>> {
        return _links.map { links ->
            val updatedLinks = links.filter { it.id != linkId }
            _links.value = updatedLinks
            Result.success(Unit)
        }
    }
}
