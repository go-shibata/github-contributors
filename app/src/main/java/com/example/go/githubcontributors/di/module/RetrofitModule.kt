package com.example.go.githubcontributors.di.module

import com.example.go.githubcontributors.data.RetrofitBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofitBase(): RetrofitBase = RetrofitBase()
}