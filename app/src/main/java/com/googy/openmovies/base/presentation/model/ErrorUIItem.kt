package com.googy.openmovies.base.presentation.model

import com.googy.openmovies.base.presentation.ViewHolderFactory
import com.googy.openmovies.base.presentation.`object`.Keys.SPAN_SIZE_SIX

data class ErrorUIItem(val textId: Int, val subTextId: Int, val imageRes: Int) : BaseUIEntity(SPAN_SIZE_SIX) {
    override fun getId(): String {
        return textId.toString()
    }

    override fun equals(other: BaseUIEntity): Boolean {
        return other is ErrorUIItem
    }

    override fun getViewType(): Int {
        return ViewHolderFactory.ViewType.ERROR
    }
}