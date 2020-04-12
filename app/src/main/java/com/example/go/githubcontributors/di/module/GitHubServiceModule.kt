package com.example.go.githubcontributors.di.module

import com.example.go.githubcontributors.data.GitHubService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class GitHubServiceModule {

    @Provides
    @Singleton
    fun provideGitHubService(): GitHubService = GitHubService()
}