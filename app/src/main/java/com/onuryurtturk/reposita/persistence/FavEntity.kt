package com.onuryurtturk.reposita.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_items")
data class FavEntity(
    /**
     * Fav items table structure
     */
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long?,
    @ColumnInfo(name = "userId") var userId : Long,
    @ColumnInfo(name = "repoId") var repoId : Long)