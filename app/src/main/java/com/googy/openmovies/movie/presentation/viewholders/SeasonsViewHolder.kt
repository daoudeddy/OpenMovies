package com.googy.openmovies.movie.presentation.viewholders

import android.view.ViewGroup
import androidx.core.view.isVisible
import com.googy.openmovies.R
import com.googy.openmovies.base.presentation.adapter.BaseAdapter
import com.googy.openmovies.base.presentation.extension.hide
import com.googy.openmovies.base.presentation.extension.show
import com.googy.openmovies.base.presentation.viewholders.BaseViewHolder
import com.googy.openmovies.movie.presentation.model.SeasonUIEntity
import kotlinx.android.synthetic.main.season_item_layout.view.*

class SeasonsViewHolder(parent: ViewGroup) : BaseViewHolder<SeasonUIEntity>(
    R.layout.season_item_layout, parent
) {
    private val adapter: BaseAdapter by lazy { BaseAdapter() }

    override fun bindView(item: SeasonUIEntity) {
        itemView.seasonTitleView.text = "Season ${item.season}"
        itemView.episodesRecyclerView.adapter = adapter
        adapter.submitList(item.episodes)
        itemView.episodesRecyclerView.hide()

        itemView.seasonTitleView.setOnClickListener {
            if (itemView.episodesRecyclerView.isVisible) {
                itemView.seasonTitleView.setIconResource(R.drawable.ic_arrow_drop_down_black_24dp)
                itemView.episodesRecyclerView.hide()
            } else {
                itemView.seasonTitleView.setIconResource(R.drawable.ic_arrow_drop_up_black_24dp)
                itemView.episodesRecyclerView.show()
            }
        }
    }
}