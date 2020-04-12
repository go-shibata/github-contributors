package com.example.go.githubcontributors.di.module

import com.example.go.githubcontributors.di.component.DetailFragmentSubcomponent
import com.example.go.githubcontributors.ui.detail.DetailFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(subcomponents = [DetailFragmentSubcomponent::class])
interface DetailFragmentModule {

    @Binds
    @IntoMap
    @ClassKey(DetailFragment::class)
    fun bindMainFragmentSubcomponentFactory(
        factory: DetailFragmentSubcomponent.Factory
    ): AndroidInjector.Factory<*>
}
