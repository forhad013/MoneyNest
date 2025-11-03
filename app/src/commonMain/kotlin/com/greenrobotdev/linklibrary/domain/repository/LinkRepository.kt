package com.greenrobotdev.linklibrary.domain.repository

import com.greenrobotdev.linklibrary.model.Link
import kotlinx.coroutines.flow.Flow

interface LinkRepository {
    suspend fun getLinks(): Flow<Result<List<Link>>>
    suspend fun toggleFavorite(linkId: String): Flow<Result<Link>>
    suspend fun addLink(link: Link): Flow<Result<Link>>
    suspend fun deleteLink(linkId: String): Flow<Result<Unit>>
}
