package com.googy.openmovies.main.data.model

import com.googy.openmovies.base.data.model.BaseDataEntity
import com.googy.openmovies.movie.domain.model.SeasonDomainEntity
import com.googy.openmovies.movie.presentation.model.SeasonUIEntity

data class Season(
    val episodes: List<Episodes>
) : BaseDataEntity<SeasonUIEntity, List<SeasonDomainEntity>>() {
    override fun toDomainEntity(): List<SeasonDomainEntity> {
        return episodes.groupBy { it.season }.map {
            SeasonDomainEntity(it.key, it.value.map { it.toDomainEntity() })
        }
    }
}