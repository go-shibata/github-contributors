package com.example.go.githubcontributors.data

import com.example.go.githubcontributors.data.model.Contributor
import com.example.go.githubcontributors.data.model.ContributorDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {
    companion object {
        const val BASE_URL = "https://api.github.com/"
    }

    @GET("repos/googlesamples/android-architecture-components/contributors")
    fun getContributors(): Call<List<Contributor>>

    @GET("users/{loginId}")
    fun getContributorDetail(@Path("loginId") loginId: String): Call<ContributorDetail>
}