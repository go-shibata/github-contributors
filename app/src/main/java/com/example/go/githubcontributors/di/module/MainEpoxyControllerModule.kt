package com.example.go.githubcontributors.di.module

import com.example.go.githubcontributors.ui.main.MainEpoxyController
import com.example.go.githubcontributors.ui.main.MainFragment
import dagger.Binds
import dagger.Module

@Module
interface MainEpoxyControllerModule {

    @Binds
    fun bindOnClickContributorListener(fragment: MainFragment): MainEpoxyController.OnClickContributorListener
}