package com.example.go.githubcontributors.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.go.githubcontributors.data.GitHubService
import com.example.go.githubcontributors.data.model.Contributor
import com.example.go.githubcontributors.data.model.ContributorDetail
import com.example.go.githubcontributors.util.SingleLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val gitHubService: GitHubService
) : ViewModel() {

    private val _contributorDetail: MutableLiveData<ContributorDetail> = MutableLiveData()
    val contributorDetail: LiveData<ContributorDetail> = _contributorDetail

    private val _onFailureFetchContributorDetail: SingleLiveData<Unit> = SingleLiveData()
    val onFailureFetchContributorDetail: LiveData<Unit> = _onFailureFetchContributorDetail

    fun setContributor(contributor: Contributor) {
        if (contributor.id == contributorDetail.value?.id) return

        // set information already obtained
        val contributorDetail = ContributorDetail(contributor)
        _contributorDetail.postValue(contributorDetail)

        fetchContributorDetail(contributor)
    }

    private fun fetchContributorDetail(contributor: Contributor) {
        gitHubService.getContributorDetail(contributor.name, object : Callback<ContributorDetail> {
            override fun onResponse(
                call: Call<ContributorDetail>,
                response: Response<ContributorDetail>
            ) {
                if (!response.isSuccessful) {
                    fail()
                    return
                }
                _contributorDetail.postValue(response.body())
            }

            override fun onFailure(call: Call<ContributorDetail>, t: Throwable) {
                fail()
            }

            fun fail() {
                _onFailureFetchContributorDetail.postValue(Unit)
            }
        })
    }
}