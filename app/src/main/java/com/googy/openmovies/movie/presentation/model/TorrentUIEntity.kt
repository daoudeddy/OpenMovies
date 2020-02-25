package com.googy.openmovies.movie.presentation.model

import com.googy.openmovies.base.presentation.ViewHolderFactory
import com.googy.openmovies.base.presentation.`object`.Keys.SPAN_SIZE_SIX
import com.googy.openmovies.base.presentation.`object`.Keys.SPAN_SIZE_THREE
import com.googy.openmovies.base.presentation.model.BaseUIEntity

data class TorrentUIEntity(
    val itemId: String,
    val durability: Double,
    val file: String,
    val language: String,
    val quality: String,
    val size_bytes: Long,
    val torrent_magnet: String,
    val torrent_peers: Int,
    val torrent_seeds: Int,
    val torrent_url: String,
    val vitality: Double
) : BaseUIEntity(SPAN_SIZE_SIX) {
    override fun getId(): String {
        return itemId
    }

    override fun equals(other: BaseUIEntity): Boolean {
        return other is TorrentUIEntity && itemId == other.itemId
    }

    override fun getViewType(): Int {
        return ViewHolderFactory.ViewType.TORRENTS
    }

    fun toButtonEntity() = ButtonUIEntity(quality, torrent_url.ifEmpty { torrent_magnet }, size_bytes)

}