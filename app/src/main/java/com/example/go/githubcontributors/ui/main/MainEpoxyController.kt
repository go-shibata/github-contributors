package com.example.go.githubcontributors.ui.main

import com.airbnb.epoxy.EpoxyController
import com.example.go.githubcontributors.data.model.Contributor
import com.example.go.githubcontributors.itemContributor
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_contributor.view.*
import javax.inject.Inject

class MainEpoxyController @Inject constructor() : EpoxyController() {

    private var data: List<Contributor> = emptyList()

    fun setData(data: List<Contributor>) {
        this.data = data
        requestModelBuild()
    }

    override fun buildModels() {
        data.forEach {
            itemContributor {
                id(it.name)
                contributor(it)

                onBind { _, view, _ ->
                    Picasso.get()
                        .load(it.avatarUrl)
                        .into(view.dataBinding.root.avatar)
                }
            }
        }
    }
}