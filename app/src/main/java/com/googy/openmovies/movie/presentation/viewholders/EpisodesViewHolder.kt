package com.googy.openmovies.movie.presentation.viewholders

import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.googy.openmovies.R
import com.googy.openmovies.base.presentation.adapter.BaseAdapter
import com.googy.openmovies.base.presentation.viewholders.BaseViewHolder
import com.googy.openmovies.movie.presentation.model.EpisodeUIEntity
import kotlinx.android.synthetic.main.episode_item_layout.view.*

class EpisodesViewHolder(parent: ViewGroup) : BaseViewHolder<EpisodeUIEntity>(
    R.layout.episode_item_layout, parent
) {
    private val adapter: BaseAdapter by lazy { BaseAdapter() }

    override fun bindView(item: EpisodeUIEntity) {
        itemView.episodeTextView.text = "${item.episode}. ${item.title}"
        itemView.summaryTextView.text = item.synopsis
        itemView.torrentsRecyclerView.adapter = adapter
        adapter.submitList(item.items.map { it.toButtonEntity() }.filter { it.torrentUrl.isNotEmpty() })
    }
}