package com.paliy_dmitriy.data.mapper

import com.paliy_dmitriy.data.local.database.entity.FavoriteEntity
import com.paliy_dmitriy.domain.model.FavoriteQuote
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteMapper @Inject constructor() {

    fun mapToDomain(entity: FavoriteEntity): FavoriteQuote {
        return FavoriteQuote(
            favoriteId = entity.favoriteId,
            quoteId = entity.quoteId,
            customName = entity.customName,
            addedDate = entity.addedDate,
            orderIndex = entity.orderIndex
        )
    }

    fun mapToEntity(domain: FavoriteQuote): FavoriteEntity {
        return FavoriteEntity(
            favoriteId = domain.favoriteId,
            quoteId = domain.quoteId,
            customName = domain.customName,
            addedDate = domain.addedDate,
            orderIndex = domain.orderIndex
        )
    }

    fun mapToEntityList(domainList: List<FavoriteQuote>): List<FavoriteEntity> {
        return domainList.map { mapToEntity(it) }
    }

    fun mapToDomainList(entityList: List<FavoriteEntity>): List<FavoriteQuote> {
        return entityList.map { mapToDomain(it) }
    }
}