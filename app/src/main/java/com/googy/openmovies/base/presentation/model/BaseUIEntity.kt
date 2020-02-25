package com.googy.openmovies.base.presentation.model

import com.googy.openmovies.base.presentation.`object`.Keys.SPAN_SIZE_SIX
import java.io.Serializable

abstract class BaseUIEntity(val spanSize: Int = SPAN_SIZE_SIX) : Serializable {
    abstract fun getId(): String
    abstract fun equals(other: BaseUIEntity): Boolean
    abstract fun getViewType(): Int
}