package com.example.go.githubcontributors.network.model

import com.google.gson.annotations.SerializedName

data class Contributor(
    @SerializedName("login") val name: String,
    val avatarUrl: String,
    val contributions: Int
)