package com.googy.openmovies.base.presentation

import android.view.ViewGroup
import com.googy.openmovies.base.presentation.ViewHolderFactory.ViewType.BUTTONS
import com.googy.openmovies.base.presentation.ViewHolderFactory.ViewType.EPISODES
import com.googy.openmovies.base.presentation.ViewHolderFactory.ViewType.ERROR
import com.googy.openmovies.base.presentation.ViewHolderFactory.ViewType.MOVIES
import com.googy.openmovies.base.presentation.ViewHolderFactory.ViewType.MOVIE_INFO
import com.googy.openmovies.base.presentation.ViewHolderFactory.ViewType.SEASONS
import com.googy.openmovies.base.presentation.ViewHolderFactory.ViewType.TORRENTS
import com.googy.openmovies.base.presentation.ViewHolderFactory.ViewType.TRAILER
import com.googy.openmovies.base.presentation.viewholders.BaseViewHolder
import com.googy.openmovies.base.presentation.viewholders.ErrorViewHolder
import com.googy.openmovies.main.presentation.viewholders.EmptyViewHolder
import com.googy.openmovies.main.presentation.viewholders.MoviesViewHolder
import com.googy.openmovies.movie.presentation.viewholders.*

object ViewHolderFactory {
    inline fun <reified I, reified T : BaseViewHolder<I>> getViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): T {
        return when (viewType) {
            MOVIES -> MoviesViewHolder(parent)
            TORRENTS -> TorrentsViewHolder(parent)
            TRAILER -> TrailerViewHolder(parent)
            MOVIE_INFO -> MovieInfoViewHolder(parent)
            SEASONS -> SeasonsViewHolder(parent)
            EPISODES -> EpisodesViewHolder(parent)
            BUTTONS -> ButtonsViewHolder(parent)
            ERROR -> ErrorViewHolder(parent)
            else -> EmptyViewHolder(parent)
        } as T
    }

    object ViewType {
        const val MOVIES = 1
        const val TORRENTS = 2
        const val TRAILER = 3
        const val MOVIE_INFO = 4
        const val SEASONS: Int = 5
        const val EPISODES = 6
        const val BUTTONS = 7
        const val ERROR: Int = 8
    }
}