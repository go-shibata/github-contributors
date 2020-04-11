package com.example.go.githubcontributors.di.module

import com.example.go.githubcontributors.di.component.MainActivitySubcomponent
import com.example.go.githubcontributors.ui.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(subcomponents = [MainActivitySubcomponent::class])
interface MainActivityModule {

    @Binds
    @IntoMap
    @ClassKey(MainActivity::class)
    fun bindMainActivitySubcomponentFactory(
        factory: MainActivitySubcomponent.Factory
    ): AndroidInjector.Factory<*>
}