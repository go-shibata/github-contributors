package com.example.go.githubcontributors.data

import com.example.go.githubcontributors.data.model.Contributor
import retrofit2.Callback
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitHubService @Inject constructor(
    private val gitHubApi: GitHubApi
) {

    fun getContributors(callback: Callback<List<Contributor>>) {
        val call = gitHubApi.getContributors()
        call.enqueue(callback)
    }
}