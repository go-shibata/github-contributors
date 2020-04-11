package com.example.go.githubcontributors.di.module

import com.example.go.githubcontributors.ui.main.MainFragment
import com.example.go.githubcontributors.ui.main.MainViewModel
import dagger.Binds
import dagger.Module

@Module
interface MainViewModelModule {

    @Binds
    fun bindOnFailureGetContributorsListener(fragment: MainFragment): MainViewModel.OnFailureGetContributorsListener
}