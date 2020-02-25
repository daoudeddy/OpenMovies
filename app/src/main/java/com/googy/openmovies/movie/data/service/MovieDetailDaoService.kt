package com.googy.openmovies.movie.data.service

import com.googy.openmovies.main.data.model.Episodes
import com.googy.openmovies.main.data.model.MovieList

class MovieDetailDaoService(private val movieDetailDao: MovieDetailDao) : MovieDetailDao {
    override fun insert(episodes: List<Episodes>) {
        movieDetailDao.insert(episodes)
    }

    override fun getEpisodes(imdbId: String): List<Episodes> {
        return movieDetailDao.getEpisodes(imdbId)
    }

    override fun getMovie(movieId: Int): MovieList {
        return movieDetailDao.getMovie(movieId)
    }

}