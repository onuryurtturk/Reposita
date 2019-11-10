package com.onuryurtturk.reposita.di

import com.onuryurtturk.reposita.RepositaApplication
import com.onuryurtturk.reposita.persistence.FavDatabase
import com.onuryurtturk.reposita.data.RepoRepository
import com.onuryurtturk.reposita.viewmodel.RepoViewModelFactory

object InjectionUtils {
    /**
     * ViewModelFactory provider
     */
    fun provideRepoViewModelFactory() : RepoViewModelFactory {
        val repository = RepoRepository.getInstance(FavDatabase.getInstance(RepositaApplication.applicationContext()).favDao())
        return RepoViewModelFactory(repository)
    }
}