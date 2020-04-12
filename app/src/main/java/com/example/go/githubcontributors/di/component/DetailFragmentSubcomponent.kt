package com.example.go.githubcontributors.di.component

import com.example.go.githubcontributors.ui.detail.DetailFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface DetailFragmentSubcomponent : AndroidInjector<DetailFragment> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<DetailFragment>
}