package com.googy.openmovies.base.presentation.view

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.R
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.googy.openmovies.base.presentation.adapter.BaseAdapter

class DynamicRecyclerView : RecyclerView {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(
        context,
        attrs,
        R.attr.recyclerViewStyle
    )

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView(context)
    }

    private fun initView(context: Context) {
        layoutManager = GridLayoutManager(context, 6).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int) =
                    adapter.getItemByPosition(position).spanSize
            }
            isItemPrefetchEnabled = true
            initialPrefetchItemCount = 8
        }
    }

    override fun getAdapter(): BaseAdapter {
        return super.getAdapter() as BaseAdapter
    }

}