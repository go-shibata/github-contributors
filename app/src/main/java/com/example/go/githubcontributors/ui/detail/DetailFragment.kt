package com.example.go.githubcontributors.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.go.githubcontributors.data.model.Contributor
import com.example.go.githubcontributors.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private lateinit var contributor: Contributor

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
