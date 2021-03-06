package com.example.go.githubcontributors.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Contributor(
    @SerializedName("login") val name: String,
    val id: Int,
    val avatarUrl: String,
    val contributions: Int
) : Serializable