package com.googy.openmovies.movie.presentation.viewholders

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.googy.openmovies.R
import com.googy.openmovies.base.presentation.extension.hide
import com.googy.openmovies.base.presentation.extension.show
import com.googy.openmovies.base.presentation.viewholders.BaseViewHolder
import com.googy.openmovies.movie.presentation.model.TrailerUIEntity
import kotlinx.android.synthetic.main.trailer_item_layout.view.*

class TrailerViewHolder(parent: ViewGroup) : BaseViewHolder<TrailerUIEntity>(
    R.layout.trailer_item_layout, parent
) {
    override fun bindView(item: TrailerUIEntity) {
        Glide.with(itemView.trailerThumbImageView)
            .load("http://img.youtube.com/vi/${item.trailerId}/0.jpg")
            .placeholder(R.drawable.movie_placeholder)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    itemView.trailerButton.hide()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    itemView.trailerButton.show()
                    return false
                }

            })
            .into(itemView.trailerThumbImageView)

        itemView.trailerButton.setOnClickListener {
            itemView.context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/watch?v=${item.trailerId}")
                )
            )
        }
    }
}