package com.paliy_dmitriy.data.local.data_base.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

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