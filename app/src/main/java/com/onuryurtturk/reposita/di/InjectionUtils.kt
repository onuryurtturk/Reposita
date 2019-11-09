package com.onuryurtturk.reposita.di

import com.onuryurtturk.reposita.RepositaApplication
import com.onuryurtturk.reposita.dao.FavDatabase
import com.onuryurtturk.reposita.data.RepoRepository
import com.onuryurtturk.reposita.viewmodel.RepoViewModelFactory

object InjectionUtils {

    fun provideRepoViewModelFactory() : RepoViewModelFactory {
        val repository = RepoRepository.getInstance(FavDatabase.getInstance(RepositaApplication.applicationContext()).favDao())
        return RepoViewModelFactory(repository)
    }
}