package com.onuryurtturk.reposita.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.onuryurtturk.reposita.data.RepoRepository

class RepoViewModelFactory(private val repoRepository: RepoRepository) : ViewModelProvider.NewInstanceFactory() {

    /**
     * Used hashmap because of sharing viewmodel between repositoryActivity and repositoryDetailActivity
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RepoViewModel::class.java)){
            val key = "RepoViewModel"
            if(viewModelHashMap.containsKey(key)){
               return getFromMap(key) as T
            }else{
                addToMap(key,RepoViewModel(repoRepository))
                return getFromMap(key) as T
            }

        }
        throw IllegalArgumentException("Different ViewModel")
    }

    companion object {
        val viewModelHashMap = HashMap<String,ViewModel>()
        fun addToMap(key:String, viewModel: ViewModel){
            viewModelHashMap.put(key,viewModel)
        }
        fun getFromMap(key: String) : ViewModel?{
            return viewModelHashMap[key]
        }
    }
}