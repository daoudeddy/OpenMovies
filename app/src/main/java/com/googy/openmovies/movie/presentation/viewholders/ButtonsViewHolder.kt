package com.googy.openmovies.movie.presentation.viewholders

import android.content.Intent
import android.net.Uri
import android.text.format.Formatter
import android.view.ViewGroup
import com.googy.openmovies.R
import com.googy.openmovies.base.presentation.viewholders.BaseViewHolder
import com.googy.openmovies.movie.presentation.model.ButtonUIEntity
import kotlinx.android.synthetic.main.button_item_view.view.*

class ButtonsViewHolder(parent: ViewGroup) : BaseViewHolder<ButtonUIEntity>(
    R.layout.button_item_view, parent
) {
    override fun bindView(item: ButtonUIEntity) {
        itemView.buttonView.text =
            "${item.quality} : ${Formatter.formatShortFileSize(itemView.context, item.sizeBytes)}"
        itemView.buttonView.setOnClickListener {
            try {
                itemView.context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(item.torrentUrl)
                    )
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}