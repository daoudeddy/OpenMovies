package com.googy.openmovies.movie.presentation.activity

import android.os.Bundle
import com.googy.openmovies.base.presentation.`object`.Keys.EXTRA_MOVIE_ID
import com.googy.openmovies.base.presentation.activity.BaseActivity
import com.googy.openmovies.base.presentation.extension.addFragment
import com.googy.openmovies.base.presentation.extension.intent
import com.googy.openmovies.main.presentation.fragment.MainFragment
import com.googy.openmovies.movie.presentation.fragment.MovieFragment

class MovieActivity : BaseActivity() {
    override fun toolbarVisible(): Boolean = true

    private val movieId: String by intent(EXTRA_MOVIE_ID, "")

    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(com.googy.openmovies.R.anim.slide_in_up, com.googy.openmovies.R.anim.slide_out_up)
        super.onCreate(savedInstanceState)
        addFragment<MovieFragment>(false, false, EXTRA_MOVIE_ID to movieId)
    }

}
