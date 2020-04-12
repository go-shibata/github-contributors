package com.example.go.githubcontributors.data.model

import com.google.gson.annotations.SerializedName

data class ContributorDetail(
    @SerializedName("login") val loginId: String,
    val id: Int,
    val avatarUrl: String,
    val name: String,
    val company: String,
    val blog: String,
    val location: String,
    val email: String?,
    val bio: String?,
    val publicRepos: Int,
    val publicGists: Int,
    val followers: Int,
    val following: Int
)