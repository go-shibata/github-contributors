package com.example.go.githubcontributors.ui.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.go.githubcontributors.data.model.Contributor
import com.example.go.githubcontributors.databinding.FragmentDetailBinding
import com.example.go.githubcontributors.di.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class DetailFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory<DetailViewModel>
    private val viewModel: DetailViewModel by activityViewModels { factory }

    private lateinit var contributor: Contributor

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkNotNull(arguments).let {
            contributor = DetailFragmentArgs.fromBundle(it).contributor
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
}
