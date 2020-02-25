package com.googy.openmovies.main.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.googy.openmovies.movie.presentation.model.TorrentUIEntity

@Entity
data class Items(
    @PrimaryKey
    val id: String,
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
) {
    fun toUIEntity() = TorrentUIEntity(
        id,
        durability,
        file,
        language,
        quality,
        size_bytes,
        torrent_magnet,
        torrent_peers,
        torrent_seeds,
        torrent_url,
        vitality
    )
}