package com.example.go.githubcontributors.data.model

import com.google.gson.annotations.SerializedName

data class ContributorDetail(
    @SerializedName("login") val loginId: String,
    val id: Int,
    val avatarUrl: String,
    val name: String? = null,
    val company: String? = null,
    val blog: String? = null,
    val location: String? = null,
    val email: String? = null,
    val bio: String? = null,
    val publicRepos: Int? = null,
    val publicGists: Int? = null,
    val followers: Int? = null,
    val following: Int? = null
) {
    constructor(contributor: Contributor) : this(
        contributor.name,
        contributor.id,
        contributor.avatarUrl
    )
}