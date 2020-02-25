package com.googy.openmovies.main.presentation.viewholders

import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.googy.openmovies.R
import com.googy.openmovies.base.presentation.viewholders.BaseViewHolder
import com.googy.openmovies.main.presentation.model.MovieUIEntity
import kotlinx.android.synthetic.main.movie_item_layout.view.*

class MoviesViewHolder(
    parent: ViewGroup
) : BaseViewHolder<MovieUIEntity>(R.layout.movie_item_layout, parent) {
    override fun bindView(item: MovieUIEntity) {
        itemView.movieNameTextView.text = item.title
        Glide.with(itemView.movieImageView).load(item.poster_med).centerCrop().into(itemView.movieImageView)
        itemView.setOnClickListener { onItemClickListener(item) }
    }
}