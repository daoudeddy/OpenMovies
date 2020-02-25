package com.googy.openmovies.main.presentation.viewholders

import android.view.ViewGroup
import com.googy.openmovies.R
import com.googy.openmovies.base.presentation.viewholders.BaseViewHolder
import com.googy.openmovies.main.presentation.model.EmptyBaseUIEntity

class EmptyViewHolder(parent: ViewGroup) : BaseViewHolder<EmptyBaseUIEntity>(R.layout.empty_layout, parent) {
    override fun bindView(item: EmptyBaseUIEntity) {

    }
}

