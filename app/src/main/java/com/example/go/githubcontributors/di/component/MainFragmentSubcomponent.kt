package com.example.go.githubcontributors.di.component

import com.example.go.githubcontributors.di.module.MainEpoxyControllerModule
import com.example.go.githubcontributors.ui.main.MainFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(
    modules = [
        MainEpoxyControllerModule::class
    ]
)
interface MainFragmentSubcomponent : AndroidInjector<MainFragment> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<MainFragment>
}