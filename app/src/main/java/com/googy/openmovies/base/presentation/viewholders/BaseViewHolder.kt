package com.googy.openmovies.base.presentation.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.googy.openmovies.base.presentation.model.BaseUIEntity

abstract class BaseViewHolder<T : BaseUIEntity>(
    layoutId: Int,
    viewGroup: ViewGroup
) : RecyclerView.ViewHolder(
    LayoutInflater.from(viewGroup.context).inflate(
        layoutId,
        viewGroup,
        false
    )
) {
    private var listener: ((item: T) -> Unit)? = null

    fun onItemClickListener(item: T) {
        listener?.invoke(item)
    }

    fun setOnItemClickListener(listener: ((item: T) -> Unit)?) {
        this.listener = listener
    }

    abstract fun bindView(item: T)
}