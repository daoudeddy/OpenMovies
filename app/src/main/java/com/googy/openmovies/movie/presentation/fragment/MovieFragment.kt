package com.googy.openmovies.movie.presentation.fragment

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.googy.openmovies.R
import com.googy.openmovies.base.presentation.`object`.Keys.EXTRA_MOVIE_ID
import com.googy.openmovies.base.presentation.`object`.Keys.EXTRA_TAB_TYPE
import com.googy.openmovies.base.presentation.extension.args
import com.googy.openmovies.base.presentation.fragment.BaseBottomSheetFragment
import com.googy.openmovies.movie.presentation.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment :
    BaseBottomSheetFragment<MovieViewModel.ViewModelState, MovieViewModel.Command, MovieViewModel>() {

    private val movieId: String by args(EXTRA_MOVIE_ID, "")
    private val type: String by args(EXTRA_TAB_TYPE, "")

    override fun initUi(view: View) {
        viewModel.getMovie(movieId.toInt(), type)
        movieRecyclerView.adapter = adapter
    }

    override val viewModel: MovieViewModel by viewModel()
    override val layoutId: Int = R.layout.fragment_movie


    override fun onViewModelCommand(command: MovieViewModel.Command) {
        when (command) {
            is MovieViewModel.Command.OnMovieReady -> {
                adapter.submitList(command.movieList)
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        bottomSheetDialog.setOnShowListener { dialog ->
            (dialog as BottomSheetDialog).apply {
                findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)?.let {
                    it.layoutParams = it.layoutParams.apply { height = getWindowHeight() }
                    BottomSheetBehavior.from(it).apply {
                        setUpdateImportantForAccessibilityOnSiblings(true)
                        peekHeight = getWindowHeight()
                        state = BottomSheetBehavior.STATE_EXPANDED
                        isFitToContents = false
                    }
                }
            }
        }

        return bottomSheetDialog
    }

    private fun getWindowHeight(): Int {
        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }
}