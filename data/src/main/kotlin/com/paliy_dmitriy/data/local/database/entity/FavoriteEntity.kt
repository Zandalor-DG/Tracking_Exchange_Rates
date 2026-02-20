package com.paliy_dmitriy.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteEntity(
  @PrimaryKey(autoGenerate = true)
  val favoriteId: Long = 0,
  @ColumnInfo(index = true)
  val quoteId: String,
  val customName: String?,
  val addedDate: Long,
  val orderIndex: Int
)