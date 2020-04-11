package com.example.go.githubcontributors.di.module

import com.example.go.githubcontributors.di.component.MainFragmentSubcomponent
import com.example.go.githubcontributors.ui.main.MainFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(subcomponents = [MainFragmentSubcomponent::class])
interface MainFragmentModule {

    @Binds
    @IntoMap
    @ClassKey(MainFragment::class)
    fun bindMainFragmentSubcomponentFactory(
        factory: MainFragmentSubcomponent.Factory
    ): AndroidInjector.Factory<*>
}
