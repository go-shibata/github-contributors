package com.example.go.githubcontributors.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.go.githubcontributors.data.GitHubService
import com.example.go.githubcontributors.data.model.Contributor
import com.example.go.githubcontributors.data.model.ContributorDetail
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
class DetailViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var mockGitHubService: GitHubService

    @Mock
    lateinit var mockCall: Call<ContributorDetail>

    @Mock
    lateinit var mockResponse: Response<ContributorDetail>

    @Mock
    lateinit var mockContributorDetailObserver: Observer<ContributorDetail>

    @Mock
    lateinit var mockOnFailureFetchContributorDetailObserver: Observer<Unit>

    private lateinit var viewModel: DetailViewModel

    @Before
    fun setUp() {
        viewModel = DetailViewModel(mockGitHubService)
        viewModel.contributorDetail.observeForever(mockContributorDetailObserver)
        viewModel.onFailureFetchContributorDetail.observeForever(
            mockOnFailureFetchContributorDetailObserver
        )
    }

    @After
    fun tearDown() {
    }

    @Test
    fun setContributor_confirmContributorDetailFlow() {
        val data = Contributor("data", 0, "AvatarUrl", 0)
        val dataDetail = ContributorDetail(data)
        viewModel.setContributor(data)

        verify(mockContributorDetailObserver, times(1)).onChanged(dataDetail)
    }

    @Test
    fun setContributor_confirmGitHubServiceGetContributorDetailCalled() {
        val data = Contributor("data", 0, "AvatarUrl", 0)
        viewModel.setContributor(data)

        verify(mockGitHubService, times(1)).getContributorDetail(eq(data.name), any())
    }

    @Test
    fun setContributor_whenFetchSuccess_confirmContributorDetailFlow() {
        val data = Contributor("data", 0, "AvatarUrl", 0)
        val dataDetail = mock<ContributorDetail>()
        whenever(mockResponse.isSuccessful).thenReturn(true)
        whenever(mockResponse.body()).thenReturn(dataDetail)
        whenever(mockGitHubService.getContributorDetail(eq(data.name), any())).then {
            val callback = it.getArgument(1) as Callback<ContributorDetail>
            callback.onResponse(mockCall, mockResponse)
        }

        viewModel.setContributor(data)
        verify(mockContributorDetailObserver, times(1)).onChanged(dataDetail)
        verify(mockOnFailureFetchContributorDetailObserver, never()).onChanged(any())
    }

    @Test
    fun setContributor_whenFetchFailure_confirmOnFailureFetchContributorDetailFlow() {
        val data = Contributor("data", 0, "AvatarUrl", 0)
        whenever(mockGitHubService.getContributorDetail(eq(data.name), any())).then {
            val callback = it.getArgument(1) as Callback<ContributorDetail>
            callback.onFailure(mockCall, Throwable())
        }

        viewModel.setContributor(data)
        verify(mockOnFailureFetchContributorDetailObserver, times(1)).onChanged(any())
    }

    @Test
    fun setContributor_whenResponseHasError_confirmOnFailureFetchContributorDetailFlow() {
        val data = Contributor("data", 0, "AvatarUrl", 0)
        whenever(mockResponse.isSuccessful).thenReturn(false)
        whenever(mockGitHubService.getContributorDetail(eq(data.name), any())).then {
            val callback = it.getArgument(1) as Callback<ContributorDetail>
            callback.onResponse(mockCall, mockResponse)
        }

        viewModel.setContributor(data)
        verify(mockOnFailureFetchContributorDetailObserver, times(1)).onChanged(any())
    }

    @Test
    fun setContributor_givenAlreadyFetchedContributor_confirmDoNothing() {
        val data = Contributor("data", 0, "AvatarUrl", 0)
        val dataDetail = ContributorDetail(data)
        viewModel.setContributor(data)
        viewModel.setContributor(data)

        verify(mockContributorDetailObserver, times(1)).onChanged(dataDetail)
        verify(mockGitHubService, times(1)).getContributorDetail(eq(data.name), any())
    }
}