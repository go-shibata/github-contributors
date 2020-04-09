package com.example.go.githubcontributors.network

import com.example.go.githubcontributors.network.model.Contributor
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("repos/googlesamples/android-architecture-components/contributors")
    fun getContributors(): Call<List<Contributor>>
}