package com.googy.openmovies.main.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ItemsLanguage(
    val durability: Double,
    val file: String,
    @PrimaryKey
    val id: String,
    val language: String,
    val quality: String,
    val size_bytes: Long,
    val torrent_magnet: String,
    val torrent_peers: Int,
    val torrent_seeds: Int,
    val torrent_url: String,
    val vitality: Double
)