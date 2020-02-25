package com.googy.openmovies.movie.presentation.model

import com.googy.openmovies.base.presentation.ViewHolderFactory
import com.googy.openmovies.base.presentation.`object`.Keys.SPAN_SIZE_THREE
import com.googy.openmovies.base.presentation.`object`.Keys.SPAN_SIZE_TWO
import com.googy.openmovies.base.presentation.model.BaseUIEntity

data class ButtonUIEntity(
    val quality: String,
    val torrentUrl: String,
    val sizeBytes: Long
) : BaseUIEntity(SPAN_SIZE_TWO) {
    override fun getId(): String {
        return torrentUrl
    }

    override fun equals(other: BaseUIEntity): Boolean {
        return other is ButtonUIEntity && other.quality == quality && other.torrentUrl == torrentUrl
    }

    override fun getViewType(): Int {
        return ViewHolderFactory.ViewType.BUTTONS
    }
}
