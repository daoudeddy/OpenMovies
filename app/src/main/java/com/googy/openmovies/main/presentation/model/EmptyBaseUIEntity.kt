package com.googy.openmovies.main.presentation.model

import com.googy.openmovies.base.presentation.model.BaseUIEntity
import java.util.*

class EmptyBaseUIEntity : BaseUIEntity() {
    override fun getId() = UUID.randomUUID().toString()
    override fun equals(other: BaseUIEntity) = false
    override fun getViewType() = 0
}