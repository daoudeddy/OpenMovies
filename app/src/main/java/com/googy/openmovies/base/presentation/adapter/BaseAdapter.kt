package com.googy.openmovies.base.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.googy.openmovies.base.presentation.ViewHolderFactory
import com.googy.openmovies.base.presentation.model.BaseUIEntity
import com.googy.openmovies.base.presentation.viewholders.BaseViewHolder

class BaseAdapter : ListAdapter<BaseUIEntity, BaseViewHolder<BaseUIEntity>>(
    DIFF_CALLBACK
) {

    override fun getItem(position: Int): BaseUIEntity {
        return super.getItem(position)
    }

    open fun getItemByPosition(position: Int) = getItem(position)

    private var listener: ((item: BaseUIEntity) -> Unit)? = null

    fun setOnItemClickListener(listener: (item: BaseUIEntity) -> Unit) {
        this.listener = listener
    }

    override fun onBindViewHolder(holder: BaseViewHolder<BaseUIEntity>, position: Int) {
        holder.setOnItemClickListener(listener)
        holder.bindView(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).getViewType()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<BaseUIEntity> {
        return ViewHolderFactory.getViewHolder(parent, viewType)
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<BaseUIEntity> =
            object : DiffUtil.ItemCallback<BaseUIEntity>() {
                override fun areItemsTheSame(
                    oldBaseUIEntity: BaseUIEntity, newBaseUIEntity: BaseUIEntity
                ): Boolean {
                    return oldBaseUIEntity.getId() == newBaseUIEntity.getId()
                }

                override fun areContentsTheSame(
                    oldBaseUIEntity: BaseUIEntity, newBaseUIEntity: BaseUIEntity
                ): Boolean {
                    return oldBaseUIEntity.equals(newBaseUIEntity)
                }
            }
    }
}