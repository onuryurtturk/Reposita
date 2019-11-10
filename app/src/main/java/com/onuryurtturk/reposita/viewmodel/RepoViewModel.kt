package com.onuryurtturk.reposita.viewmodel

import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.onuryurtturk.reposita.persistence.FavEntity
import com.onuryurtturk.reposita.data.RepoRepository
import com.onuryurtturk.reposita.model.Repo

class RepoViewModel(private val repoRepository: RepoRepository):ViewModel(){

    /**
     * utility views variables
     * empty : when there is no data
     * loading : when waiting for api request's result
     * keyboardClose : close keyboard after submit clicked
     */
    var empty: ObservableInt  = ObservableInt(View.VISIBLE)
    var loading: ObservableInt =  ObservableInt(View.GONE)
    var keyboardClose= MutableLiveData<Boolean> ()

    val reposLive = MutableLiveData<List<Repo>> ()
    var mSelectedPosition = MutableLiveData<Int>()
    var username = MutableLiveData<String>()

    /**
     * get repositories from api and set to live data
     */
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

    /**
     * Control repo list to handle fav changes
     */
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

    /**
     * Remove repository from fav list
     */
    fun unFavRepo(userId: Long,repoId: Long) = repoRepository.unFavRepo(userId,repoId)

    /**
     * Add repository to fav list
     */
    fun favRepo(fav: FavEntity) = repoRepository.favRepo(fav)

    /**
     * When click performed, detail activity will open
     */
    fun onRepoItemClick(position: Int){
        mSelectedPosition.value =position
    }

    fun getRepoByIndex(index: Int?): Repo? {
        return if (reposLive.value != null && index != null && reposLive.value!!.size > index) {
            reposLive.value!![index]
        } else null
    }

    /**
     * Click to submit and fetch users repositories
     */
    fun onSubmitClick() {
       getRepositories()
    }

}