package com.googy.openmovies.movie.presentation.viewholders

import android.view.ViewGroup
import androidx.core.text.parseAsHtml
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.googy.openmovies.R
import com.googy.openmovies.base.presentation.extension.showOrHide
import com.googy.openmovies.base.presentation.viewholders.BaseViewHolder
import com.googy.openmovies.movie.presentation.model.MovieInfoUIEntity
import kotlinx.android.synthetic.main.movie_info_item_layout.view.*

class MovieInfoViewHolder(parent: ViewGroup) : BaseViewHolder<MovieInfoUIEntity>(
    R.layout.movie_info_item_layout, parent
) {
    override fun bindView(item: MovieInfoUIEntity) {
        Glide.with(itemView.movieThumbImageView)
            .applyDefaultRequestOptions(RequestOptions().transform(RoundedCorners(6)))
            .load(item.poster)
            .into(itemView.movieThumbImageView)

        itemView.movieTitleTextView.text = item.title
        itemView.releaseInfoTextView.text = "${item.year} • ${item.runtime}min"
        itemView.ratingTextView.text = "${item.rating} ★"
        itemView.descriptionTextView.text = item.description
        itemView.genreTextView.text = item.genres.joinToString(separator = ", ") { it.capitalize() }
        itemView.genreTextView.showOrHide(item.genres.isNotEmpty())
        itemView.actorsTextView.text = "<b>Starring</b> ${item.actors}".parseAsHtml()
        itemView.actorsTextView.showOrHide(item.actors)
        itemView.directorsTextView.text = "<b>Directed by</b> ${item.directors}".parseAsHtml()
        itemView.directorsTextView.showOrHide(item.directors)
    }
}