package com.googy.openmovies.main.presentation.activity

import android.os.Bundle
import android.view.Menu
import com.claudiodegio.msv.OnSearchViewListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.googy.openmovies.R
import com.googy.openmovies.base.presentation.`object`.Keys.EXTRA_TAB_TYPE
import com.googy.openmovies.base.presentation.`object`.Keys.TYPE_MOVIE
import com.googy.openmovies.base.presentation.`object`.Keys.TYPE_SERIES
import com.googy.openmovies.base.presentation.activity.BaseActivity
import com.googy.openmovies.base.presentation.extension.addFragment
import com.googy.openmovies.base.presentation.extension.newFragment
import com.googy.openmovies.base.presentation.extension.throttleLatest
import com.googy.openmovies.main.presentation.fragment.MainFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(), OnSearchViewListener {

    override fun onQueryTextSubmit(p0: String): Boolean {
        throttleLatest(getCurrentFragment()::onQueryTextSubmit).invoke(p0)
        return true
    }

    override fun onSearchViewClosed() {
        getCurrentFragment().onSearchViewClosed()
    }

    override fun onQueryTextChange(p0: String) {
        throttleLatest(getCurrentFragment()::onQueryTextSubmit).invoke(p0)
    }

    override fun onSearchViewShown() {

    }

    override fun toolbarVisible(): Boolean = true
    override fun layoutId(): Int = R.layout.activity_main
    private val movieFragment: MainFragment by lazy { newFragment<MainFragment>(EXTRA_TAB_TYPE to TYPE_MOVIE) }
    private val seriesFragment: MainFragment by lazy { newFragment<MainFragment>(EXTRA_TAB_TYPE to TYPE_SERIES) }

    private var navigationItemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_movie -> {
                    searchView.closeSearch()
                    addFragment(movieFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_series -> {
                    searchView.closeSearch()
                    addFragment(seriesFragment)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment(movieFragment)
        searchView.setOnSearchViewListener(this)
    }

    override fun onResume() {
        super.onResume()
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener)
    }

    override fun onPause() {
        super.onPause()
        bottomNavigation.setOnNavigationItemSelectedListener(null)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val item = menu.findItem(R.id.action_search)
        searchView.setMenuItem(item)
        return super.onCreateOptionsMenu(menu)
    }

    private fun getCurrentFragment(): MainFragment {
        return if (bottomNavigation.selectedItemId == R.id.navigation_movie) movieFragment else seriesFragment
    }
}
