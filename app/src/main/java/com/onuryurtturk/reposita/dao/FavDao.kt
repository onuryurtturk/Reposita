package com.onuryurtturk.reposita.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavDao {

    @Query("SELECT * FROM fav_items")
    fun getAllFavs() : List<FavEntity>

    @Query("SELECT * FROM fav_items WHERE userId = :userId")
    fun findFavByUserId(userId : Long) : List<FavEntity>

    @Query("SELECT * FROM fav_items WHERE repoId = :repoId")
    fun findFavByRepoId(repoId : Long) : List<FavEntity>

    @Query("DELETE FROM fav_items WHERE userId = :userId AND repoId = :repoId")
    fun unFavRepo(userId : Long,repoId : Long)

    @Insert
    fun favRepo(vararg fav:FavEntity)

}