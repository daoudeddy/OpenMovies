package com.googy.openmovies.movie.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.googy.openmovies.R
import com.googy.openmovies.base.presentation.`object`.Keys.TYPE_SERIES
import com.googy.openmovies.base.presentation.model.BaseUIEntity
import com.googy.openmovies.base.presentation.model.ErrorUIItem
import com.googy.openmovies.base.presentation.viewmodel.BaseViewModel
import com.googy.openmovies.movie.domain.usecase.MovieDetailsUseCase
import com.googy.openmovies.movie.domain.usecase.SeriesSeasonsUseCase
import com.googy.openmovies.movie.presentation.model.SeasonUIEntity

class MovieViewModel(
    private val movieDetailsUseCase: MovieDetailsUseCase,
    private val seriesSeasonsUseCase: SeriesSeasonsUseCase
) : BaseViewModel<MovieViewModel.ViewModelState, MovieViewModel.Command>(ViewModelState()) {
    override val commandLiveData: MutableLiveData<Command> = MutableLiveData()

    fun getMovie(movieId: Int, type: String) {
        movieDetailsUseCase(
            MovieDetailsUseCase.Params(movieId),
            parentScope = viewModelScope,
            onSuccess = {
                if (type == TYPE_SERIES) getSeries(it.imdb, it.toUIEntities())
                else postCommand(Command.OnMovieReady(it.toUIEntities()))
            }, onFailure = {
                Command.OnMovieReady(mutableListOf<BaseUIEntity>().apply {
                    add(
                        ErrorUIItem(
                            R.string.internet_problem,
                            R.string.internet_connection_seems_to_me_down,
                            R.drawable.ic_wifi_strength_off_outline
                        )
                    )
                })
            })
    }

    private fun getSeries(
        imdbId: String,
        uiEntities: MutableList<BaseUIEntity>
    ) {
        seriesSeasonsUseCase(SeriesSeasonsUseCase.Params(imdbId, true), onSuccess = {
            if (it.isNotEmpty()) {
                postCommand(
                    Command.OnMovieReady(mutableListOf<BaseUIEntity>().apply {
                        addAll(uiEntities)
                        addAll(it.map { it.toUiEntity() })
                    })
                )
                setState { copy(isLoaded = true) }
            }
            getEpisodes(imdbId, uiEntities)
        }, onFailure = {
            postCommand(
                Command.OnMovieReady(mutableListOf<BaseUIEntity>().apply {
                    addAll(uiEntities)
                    add(
                        ErrorUIItem(
                            R.string.internet_problem,
                            R.string.internet_connection_seems_to_me_down,
                            R.drawable.ic_wifi_strength_off_outline
                        )
                    )
                })
            )
        }, parentScope = viewModelScope)
    }

    private fun getEpisodes(
        imdbId: String,
        uiEntities: MutableList<BaseUIEntity>
    ) {
        seriesSeasonsUseCase(SeriesSeasonsUseCase.Params(imdbId, false), onSuccess = {
            postCommand(
                Command.OnMovieReady(mutableListOf<BaseUIEntity>().apply {
                    addAll(uiEntities)
                    addAll(it.map { it.toUiEntity() })
                })
            )
        }, onFailure = {
            if (!getState().isLoaded)
                postCommand(
                    Command.OnMovieReady(mutableListOf<BaseUIEntity>().apply {
                        addAll(uiEntities)
                        add(
                            ErrorUIItem(
                                R.string.internet_problem,
                                R.string.internet_connection_seems_to_me_down,
                                R.drawable.ic_wifi_strength_off_outline
                            )
                        )
                    })
                )
        }, parentScope = viewModelScope)
    }

    sealed class Command : BaseViewModel.BaseCommand() {
        data class OnMovieReady(val movieList: MutableList<BaseUIEntity>) : Command()
        data class OnSeriesReady(val seasons: List<SeasonUIEntity>) : Command()
    }

    data class ViewModelState(val isLoaded: Boolean = false)
}