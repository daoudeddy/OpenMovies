package com.googy.openmovies.main.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.googy.openmovies.R
import com.googy.openmovies.base.domain.model.Result
import com.googy.openmovies.base.presentation.model.BaseUIEntity
import com.googy.openmovies.base.presentation.model.ErrorUIItem
import com.googy.openmovies.base.presentation.viewmodel.BaseViewModel
import com.googy.openmovies.main.domain.model.MovieDomainEntity
import com.googy.openmovies.main.domain.usecase.MoviesUseCase

class MainViewModel(
    private val moviesUseCase: MoviesUseCase
) : BaseViewModel<MainViewModel.ViewModelState, MainViewModel.MainCommand>(ViewModelState(page = 1)) {
    override val commandLiveData: MutableLiveData<MainCommand> = MutableLiveData()

    sealed class MainCommand : BaseViewModel.BaseCommand() {
        data class SearchDataCompleted(val list: List<BaseUIEntity>) : MainCommand()
        data class LocalDataCompleted(val list: MutableList<BaseUIEntity>) : MainCommand()
        data class RemoteDataCompleted(val list: List<BaseUIEntity>) : MainCommand()
        data class DataLoadingFailed(val list: MutableList<BaseUIEntity>) : MainCommand()
    }

    fun getMovies(type: String) = moviesUseCase.getMoviesLiveData(
        type = type,
        onSuccess = ::onLocalDataSuccess,
        onFailure = ::onFailure,
        parentScope = viewModelScope
    )

    fun getRemoteMovies(type: String) = moviesUseCase(
        MoviesUseCase.Params(type, ""),
        onSuccess = ::onRemoteDataSuccess,
        onFailure = ::onFailure,
        parentScope = viewModelScope
    )

    private fun onLocalDataSuccess(moviesList: List<MovieDomainEntity>) {
        postCommand(MainCommand.LocalDataCompleted(moviesList.map { it.toUiEntity() }.toMutableList()))
    }

    private fun onRemoteDataSuccess(moviesList: List<MovieDomainEntity>) {
        postCommand(MainCommand.RemoteDataCompleted(moviesList.map { it.toUiEntity() }))
    }

    private fun onSuccess(moviesList: List<MovieDomainEntity>) {
        postCommand(MainCommand.SearchDataCompleted(moviesList.map { it.toUiEntity() }))
    }

    private fun onFailure(error: Result.Error) {
        postCommand(MainCommand.DataLoadingFailed(mutableListOf<BaseUIEntity>().apply {
            add(
                ErrorUIItem(
                    R.string.internet_problem,
                    R.string.internet_connection_seems_to_me_down,
                    R.drawable.ic_wifi_strength_off_outline
                )
            )
        }))
    }

    fun getMovies(type: String, query: String) {
        moviesUseCase(
            MoviesUseCase.Params(type = type, query = query),
            onSuccess = ::onSuccess,
            onFailure = ::onFailure,
            parentScope = viewModelScope
        )
    }

    data class ViewModelState(val page: Int)
}