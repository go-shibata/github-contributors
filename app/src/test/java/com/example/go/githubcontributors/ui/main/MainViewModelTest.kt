package com.example.go.githubcontributors.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.go.githubcontributors.data.RetrofitBase
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
    lateinit var mockRetrofitBase: RetrofitBase

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
        viewModel = MainViewModel(mockRetrofitBase)
        viewModel.contributors.observeForever(mockContributorsObserver)
        viewModel.onFailureFetchContributors.observeForever(mockOnFailureFetchContributorsObserver)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun initViewModel_confirmGetContributorsCalled() {
        verify(mockRetrofitBase, times(1)).getContributors(any())
    }

    @Test
    fun fetchContributors_confirmGetContributorsCalled() {
        viewModel.fetchContributors()
        // include init of MainViewModel
        verify(mockRetrofitBase, times(2)).getContributors(any())
    }

    @Test
    fun fetchContributors_whenSuccess_confirmContributorsFlow() {
        val data = listOf(
            Contributor("test1", 1, "url1", "html1", 100),
            Contributor("test2", 2, "url2", "html2", 200)
        )
        whenever(mockResponse.isSuccessful).thenReturn(true)
        whenever(mockResponse.body()).thenReturn(data)
        whenever(mockRetrofitBase.getContributors(any())).then {
            val callback = it.getArgument(0) as Callback<List<Contributor>>
            callback.onResponse(mockCall, mockResponse)
        }

        viewModel.fetchContributors()
        verify(mockContributorsObserver, times(1)).onChanged(data)
        verify(mockOnFailureFetchContributorsObserver, never()).onChanged(any())
    }

    @Test
    fun fetchContributors_whenFailure_confirmOnFailureFetchContributorsFlow() {
        whenever(mockRetrofitBase.getContributors(any())).then {
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
        whenever(mockRetrofitBase.getContributors(any())).then {
            val callback = it.getArgument(0) as Callback<List<Contributor>>
            callback.onResponse(mockCall, mockResponse)
        }

        viewModel.fetchContributors()
        verify(mockOnFailureFetchContributorsObserver, times(1)).onChanged(any())
        verify(mockContributorsObserver, never()).onChanged(any())
    }
}