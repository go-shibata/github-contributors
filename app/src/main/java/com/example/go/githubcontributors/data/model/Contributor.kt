package com.example.go.githubcontributors.data.model

import com.google.gson.annotations.SerializedName

data class Contributor(
    @SerializedName("login") val name: String,
    val id: Int,
    val avatarUrl: String,
    val htmlUrl: String,
    val contributions: Int
)