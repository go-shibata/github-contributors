package com.example.go.githubcontributors.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.go.githubcontributors.databinding.MainFragmentBinding
import com.example.go.githubcontributors.di.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MainFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory<MainViewModel>
    private val viewModel: MainViewModel by activityViewModels { factory }

    @Inject
    lateinit var epoxyController: MainEpoxyController

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = MainFragmentBinding.inflate(inflater, container, false)
        binding.listContributors.apply {
            setController(epoxyController)
        }

        viewModel.contributors.observe(this.viewLifecycleOwner, Observer {
            epoxyController.setData(it)
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchContributors()
    }
}