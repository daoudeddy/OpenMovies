package com.googy.openmovies.movie.presentation.model

import com.googy.openmovies.base.presentation.ViewHolderFactory
import com.googy.openmovies.base.presentation.`object`.Keys.SPAN_SIZE_SIX
import com.googy.openmovies.base.presentation.model.BaseUIEntity

data class TrailerUIEntity(
    val trailerId: String
) : BaseUIEntity(SPAN_SIZE_SIX) {
    override fun getId(): String {
        return trailerId
    }

    override fun equals(other: BaseUIEntity): Boolean {
        return other is TrailerUIEntity && trailerId == other.trailerId
    }

    override fun getViewType(): Int {
        return ViewHolderFactory.ViewType.TRAILER
    }
}