package com.googy.openmovies.movie.presentation.viewholders

import android.content.Intent
import android.net.Uri
import android.text.format.Formatter
import android.view.ViewGroup
import com.googy.openmovies.R
import com.googy.openmovies.base.presentation.viewholders.BaseViewHolder
import com.googy.openmovies.movie.presentation.model.TorrentUIEntity
import kotlinx.android.synthetic.main.torrent_item_layout.view.*


class TorrentsViewHolder(parent: ViewGroup) : BaseViewHolder<TorrentUIEntity>(
    R.layout.torrent_item_layout, parent
) {
    override fun bindView(item: TorrentUIEntity) {
        itemView.torrentQualityTextView.text = item.quality
        itemView.torrentSizeTextView.text = Formatter.formatShortFileSize(
            itemView.context,
            item.size_bytes
        )
        itemView.torrentUrlButton.setOnClickListener {
            try {
                itemView.context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(item.torrent_url.ifEmpty { item.torrent_magnet })
                    )
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}