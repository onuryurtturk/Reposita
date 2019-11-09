package com.onuryurtturk.reposita.data

import com.onuryurtturk.reposita.dao.FavDao
import com.onuryurtturk.reposita.dao.FavEntity
import com.onuryurtturk.reposita.model.Repo
import com.onuryurtturk.reposita.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RepoRepository(private val favDao: FavDao) {


    companion object {
        private var INSTANCE: RepoRepository? = null

        fun getInstance(favDao: FavDao) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: RepoRepository(favDao).also {
                INSTANCE = it
            }
        }
    }

    fun getRepositories(username :String,onResult: (isSuccess: Boolean, response: List<Repo>?) -> Unit) {
        ApiClient.build()?.getRepoList(username)?.enqueue(object : Callback<List<Repo>> {
            override fun onResponse(call: Call<List<Repo>>?, response: Response<List<Repo>>?) {
                if (response != null && response.isSuccessful)
                    onResult(true, response.body()!!)
                else
                    onResult(false, null)
            }

            override fun onFailure(call: Call<List<Repo>>?, t: Throwable?) {
                onResult(false, null)
            }
        })
    }

    fun getAllFavs(): List<FavEntity>{
        return favDao.getAllFavs()
    }
    fun findFavByUserId(userId : Long):List<FavEntity>{
        return favDao.findFavByUserId(userId)
    }
    fun findFavByRepoId(repoId : Long):List<FavEntity>{
        return favDao.findFavByRepoId(repoId)
    }
    fun unFavRepo(userId: Long,repoId: Long){
        favDao.unFavRepo(userId,repoId)
    }
    fun favRepo(fav:FavEntity){
        favDao.favRepo(fav)
    }




}