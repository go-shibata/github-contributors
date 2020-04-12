package com.example.go.githubcontributors.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.go.githubcontributors.data.GitHubService
import com.example.go.githubcontributors.data.model.Contributor
import com.nhaarman.mockitokotlin2.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var mockGitHubService: GitHubService

    @Mock
    lateinit var mockCall: Call<List<Contributor>>

    @Mock
    lateinit var mockResponse: Response<List<Contributor>>

    @Mock
    lateinit var mockContributorsObserver: Observer<List<Contributor>>

    @Mock
    lateinit var mockOnFailureFetchContributorsObserver: Observer<Unit>

    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        viewModel = MainViewModel(mockGitHubService)
        viewModel.contributors.observeForever(mockContributorsObserver)
        viewModel.onFailureFetchContributors.observeForever(mockOnFailureFetchContributorsObserver)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun initViewModel_confirmGetContributorsCalled() {
        verify(mockGitHubService, times(1)).getContributors(any())
    }

    @Test
    fun fetchContributors_confirmGetContributorsCalled() {
        viewModel.fetchContributors()
        // include init of MainViewModel
        verify(mockGitHubService, times(2)).getContributors(any())
    }

    @Test
    fun fetchContributors_whenSuccess_confirmContributorsFlow() {
        val data = mock<List<Contributor>>()
        whenever(mockResponse.isSuccessful).thenReturn(true)
        whenever(mockResponse.body()).thenReturn(data)
        whenever(mockGitHubService.getContributors(any())).then {
            val callback = it.getArgument(0) as Callback<List<Contributor>>
            callback.onResponse(mockCall, mockResponse)
        }

        viewModel.fetchContributors()
        verify(mockContributorsObserver, times(1)).onChanged(data)
        verify(mockOnFailureFetchContributorsObserver, never()).onChanged(any())
    }

    @Test
    fun fetchContributors_whenFailure_confirmOnFailureFetchContributorsFlow() {
        whenever(mockGitHubService.getContributors(any())).then {
            val callback = it.getArgument(0) as Callback<List<Contributor>>
            callback.onFailure(mockCall, Throwable())
        }

        viewModel.fetchContributors()
        verify(mockOnFailureFetchContributorsObserver, times(1)).onChanged(any())
        verify(mockContributorsObserver, never()).onChanged(any())
    }

    @Test
    fun fetchContributors_whenResponseHasError_confirmOnFailureFetchContributorsFlow() {
        whenever(mockResponse.isSuccessful).thenReturn(false)
        whenever(mockGitHubService.getContributors(any())).then {
            val callback = it.getArgument(0) as Callback<List<Contributor>>
            callback.onResponse(mockCall, mockResponse)
        }

        viewModel.fetchContributors()
        verify(mockOnFailureFetchContributorsObserver, times(1)).onChanged(any())
        verify(mockContributorsObserver, never()).onChanged(any())
    }
}