package com.googy.openmovies.base.domain.model

import com.googy.openmovies.base.presentation.model.BaseUIEntity
import java.io.Serializable

abstract class BaseDomainEntity<out T : BaseUIEntity> : Serializable {
    abstract fun toUiEntity(): T
}

