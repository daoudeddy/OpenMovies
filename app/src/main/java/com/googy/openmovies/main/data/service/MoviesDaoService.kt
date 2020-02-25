package com.googy.openmovies.main.data.service

import com.googy.openmovies.main.data.model.MovieList

class MoviesDaoService(private val moviesDao: MoviesDao) : MoviesDao {
    override fun delete(ids: List<Int>, type: String) {
        moviesDao.delete(ids, type)
    }

    override fun getMovies(type: String, search: Boolean): List<MovieList> {
        return moviesDao.getMovies(type, search)
    }

    override fun insert(movies: List<MovieList>) {
        moviesDao.insert(movies)
    }

    override fun deleteAll() {
        moviesDao.deleteAll()
    }
}