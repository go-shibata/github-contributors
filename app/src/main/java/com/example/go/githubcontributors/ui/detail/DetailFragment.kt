package com.example.go.githubcontributors.ui.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.go.githubcontributors.databinding.FragmentDetailBinding
import com.example.go.githubcontributors.di.ViewModelFactory
import com.squareup.picasso.Picasso
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class DetailFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory<DetailViewModel>
    private val viewModel: DetailViewModel by viewModels { factory }

    private lateinit var binding: FragmentDetailBinding

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkNotNull(arguments).let {
            val contributor = DetailFragmentArgs.fromBundle(it).contributor
            viewModel.fetchContributorDetail(contributor)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false).apply {
            viewModel = this@DetailFragment.viewModel
            lifecycleOwner = this@DetailFragment.viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.contributorDetail.observe(viewLifecycleOwner, Observer {
            Picasso.get()
                .load(it.avatarUrl)
                .into(binding.image)
        })
    }
}
