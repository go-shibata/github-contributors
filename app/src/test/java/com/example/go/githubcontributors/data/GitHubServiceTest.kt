package com.example.go.githubcontributors.data

import com.example.go.githubcontributors.data.model.Contributor
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Callback

@RunWith(MockitoJUnitRunner::class)
class GitHubServiceTest {

    @Mock
    lateinit var mockGitHubApi: GitHubApi

    private lateinit var gitHubService: GitHubService

    @Before
    fun setUp() {
        gitHubService = GitHubService(mockGitHubApi)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getContributors() {
        val mockCall = mock<Call<List<Contributor>>>()
        val mockCallback = mock<Callback<List<Contributor>>>()
        whenever(mockGitHubApi.getContributors()).thenReturn(mockCall)

        gitHubService.getContributors(mockCallback)
        verify(mockGitHubApi, times(1)).getContributors()
        verify(mockCall, times(1)).enqueue(mockCallback)
    }
}