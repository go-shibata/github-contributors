package com.example.go.githubcontributors.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.go.githubcontributors.data.RetrofitBase
import com.example.go.githubcontributors.data.model.Contributor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel() {

    private val _contributors: MutableLiveData<List<Contributor>> = MutableLiveData()
    val contributors: LiveData<List<Contributor>> = _contributors

    fun fetchContributors() {
        RetrofitBase().getContributors(object : Callback<List<Contributor>> {
            override fun onResponse(
                call: Call<List<Contributor>>,
                response: Response<List<Contributor>>
            ) {
                if (!response.isSuccessful) return
                val list = response.body() ?: return
                _contributors.postValue(list)
            }

            override fun onFailure(call: Call<List<Contributor>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}