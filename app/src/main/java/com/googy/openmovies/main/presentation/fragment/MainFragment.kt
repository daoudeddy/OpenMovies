package com.googy.openmovies.main.presentation.fragment

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.googy.openmovies.R
import com.googy.openmovies.base.presentation.`object`.Keys.EXTRA_MOVIE_ID
import com.googy.openmovies.base.presentation.`object`.Keys.EXTRA_TAB_TYPE
import com.googy.openmovies.base.presentation.extension.args
import com.googy.openmovies.base.presentation.extension.newFragment
import com.googy.openmovies.base.presentation.extension.openBottomSheet
import com.googy.openmovies.base.presentation.fragment.BaseFragment
import com.googy.openmovies.base.presentation.model.BaseUIEntity
import com.googy.openmovies.main.data.model.MovieList
import com.googy.openmovies.main.presentation.viewmodel.MainViewModel
import com.googy.openmovies.movie.presentation.fragment.MovieFragment
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment :
    BaseFragment<MainViewModel.ViewModelState, MainViewModel.MainCommand, MainViewModel>(),
    Observer<List<MovieList>> {

    private val gridLayoutManager: GridLayoutManager by lazy { GridLayoutManager(context, 2) }

    override fun onChanged(list: List<MovieList>) {
        adapter.submitList(list.map { it.toDomainEntity() }.map { it.toUiEntity() })
    }

    override val viewModel: MainViewModel by viewModel()
    override val layoutId: Int = R.layout.fragment_main

    private val type: String by args(EXTRA_TAB_TYPE, "list")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter.setOnItemClickListener(::onItemClickListener)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getMovies(type)
    }

    private fun onItemClickListener(item: BaseUIEntity) {
        openBottomSheet(
            newFragment<MovieFragment>(
                EXTRA_MOVIE_ID to item.getId(),
                EXTRA_TAB_TYPE to type
            )
        )
    }

    override fun initUi(view: View) {
        recyclerView.adapter = adapter
    }

    override fun onViewModelCommand(command: MainViewModel.MainCommand) {
        when (command) {
            is MainViewModel.MainCommand.SearchDataCompleted -> {
                adapter.submitList(command.list)
            }
            is MainViewModel.MainCommand.RemoteDataCompleted -> {
                adapter.submitList(command.list)
            }
            is MainViewModel.MainCommand.LocalDataCompleted -> {
                adapter.submitList(command.list)
                viewModel.getRemoteMovies(type)
            }
            is MainViewModel.MainCommand.DataLoadingFailed -> {
                adapter.submitList(command.list)
            }
        }
    }

    fun onQueryTextSubmit(query: String) {
        viewModel.getMovies(type, query)
    }

    fun onSearchViewClosed() {
        viewModel.getMovies(type)
    }

}