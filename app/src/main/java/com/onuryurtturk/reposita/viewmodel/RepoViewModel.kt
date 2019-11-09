package com.onuryurtturk.reposita.viewmodel

import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.onuryurtturk.reposita.dao.FavEntity
import com.onuryurtturk.reposita.data.RepoRepository
import com.onuryurtturk.reposita.model.Repo

class RepoViewModel(private val repoRepository: RepoRepository):ViewModel(){

    var empty: ObservableInt  = ObservableInt(View.VISIBLE)
    var loading: ObservableInt =  ObservableInt(View.GONE)
    var keyboardClose= MutableLiveData<Boolean> ()

    val reposLive = MutableLiveData<List<Repo>> ()
    var mSelectedPosition = MutableLiveData<Int>()
    var username = MutableLiveData<String>()

    fun getRepositories(){
        loading.set(View.VISIBLE)
        repoRepository.getRepositories(username.value.toString()) { isSuccess, response ->   loading.set(View.GONE)
            if(isSuccess){
                keyboardClose.value = true
                reposLive.value = response
                checkForStar()
                empty.set(View.GONE)
            }else{
                keyboardClose.value = false
                empty.set(View.VISIBLE)
            }
        }
    }

    fun checkForStar(){
        val favList = repoRepository.getAllFavs()
        for(repo in reposLive.value!!){
            for (fav in favList){
                if(repo.owner.id == fav.userId && repo.id == fav.repoId){
                    repo.faved = true
                }
            }
        }

    }

    fun findFavItemByUserId(userId: Long) = repoRepository.findFavByUserId(userId)
    fun findFavItemByRepoId(repoId: Long) = repoRepository.findFavByRepoId(repoId)
    fun unFavRepo(userId: Long,repoId: Long) = repoRepository.unFavRepo(userId,repoId)
    fun favRepo(fav: FavEntity) = repoRepository.favRepo(fav)

    fun onRepoItemClick(position: Int){
        mSelectedPosition.value =position
    }

    fun getRepoByIndex(index: Int?): Repo? {
        return if (reposLive.value != null && index != null && reposLive.value!!.size > index) {
            reposLive.value!![index]
        } else null
    }

    fun onSubmitClick() {
       getRepositories()
    }

}