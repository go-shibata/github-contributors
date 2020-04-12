package com.example.go.githubcontributors.di.component

import android.app.Application
import com.example.go.githubcontributors.App
import com.example.go.githubcontributors.di.module.DetailFragmentModule
import com.example.go.githubcontributors.di.module.GitHubApiModule
import com.example.go.githubcontributors.di.module.MainActivityModule
import com.example.go.githubcontributors.di.module.MainFragmentModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        GitHubApiModule::class,
        MainActivityModule::class,
        MainFragmentModule::class,
        DetailFragmentModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }

    fun inject(app: App)
}