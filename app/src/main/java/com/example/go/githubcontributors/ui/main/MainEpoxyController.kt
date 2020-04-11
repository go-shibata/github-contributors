package com.example.go.githubcontributors.ui.main

import com.airbnb.epoxy.EpoxyController
import com.example.go.githubcontributors.data.model.Contributor
import com.example.go.githubcontributors.itemContributor
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_contributor.view.*
import javax.inject.Inject

class MainEpoxyController @Inject constructor() : EpoxyController() {

    private var data: List<Contributor> = emptyList()
    private var listener: OnClickContributorListener? = null

    fun setOnClickContributionListener(listener: OnClickContributorListener) {
        this.listener = listener
    }

    fun setData(data: List<Contributor>) {
        this.data = data
        requestModelBuild()
    }

    override fun buildModels() {
        data.forEach {
            itemContributor {
                id(it.id)
                contributor(it)

                onBind { _, view, _ ->
                    Picasso.get()
                        .load(it.avatarUrl)
                        .into(view.dataBinding.root.avatar)

                    view.dataBinding.root.setOnClickListener { _ ->
                        listener?.onClickContributor(it)
                    }
                }
            }
        }
    }

    interface OnClickContributorListener {
        fun onClickContributor(contributor: Contributor)
    }
}