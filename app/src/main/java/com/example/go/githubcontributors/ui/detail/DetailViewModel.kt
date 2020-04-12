package com.example.go.githubcontributors.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.go.githubcontributors.data.model.Contributor
import javax.inject.Inject

class DetailViewModel @Inject constructor() : ViewModel() {

    private val _contributor: MutableLiveData<Contributor> = MutableLiveData()
    val contributor: LiveData<Contributor> = _contributor

    fun setContributor(contributor: Contributor) {
        _contributor.postValue(contributor)
    }
}