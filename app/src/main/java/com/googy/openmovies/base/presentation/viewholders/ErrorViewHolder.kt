package com.googy.openmovies.base.presentation.viewholders

import android.view.ViewGroup
import com.googy.openmovies.R
import com.googy.openmovies.base.presentation.model.ErrorUIItem
import kotlinx.android.synthetic.main.error_item_layout.view.*

class ErrorViewHolder(
    parent: ViewGroup
) : BaseViewHolder<ErrorUIItem>(R.layout.error_item_layout, parent) {
    override fun bindView(item: ErrorUIItem) {
        itemView.errorImageView.setImageResource(item.imageRes)
        itemView.errorTextView.setText(item.textId)
        itemView.errorSubTextView.setText(item.subTextId)
    }
}