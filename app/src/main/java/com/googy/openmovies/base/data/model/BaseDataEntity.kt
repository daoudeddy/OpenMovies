package com.googy.openmovies.base.data.model

import com.googy.openmovies.base.presentation.model.BaseUIEntity

abstract class BaseDataEntity<out S : BaseUIEntity, out T> {
    abstract fun toDomainEntity(): T
}
