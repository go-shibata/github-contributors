package com.example.go.githubcontributors.data

import com.example.go.githubcontributors.data.model.Contributor
import retrofit2.Call
import retrofit2.http.GET

interface GitHubApi {
    @GET("repos/googlesamples/android-architecture-components/contributors")
    fun getContributors(): Call<List<Contributor>>
}