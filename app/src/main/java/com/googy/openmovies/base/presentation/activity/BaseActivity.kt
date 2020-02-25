package com.googy.openmovies.base.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.googy.openmovies.R
import kotlinx.android.synthetic.main.activity_base.*


abstract class BaseActivity : AppCompatActivity() {

    abstract fun toolbarVisible(): Boolean
    open fun layoutId() = R.layout.activity_base

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        initUIMode()
        setUpToolbar()
    }

    private fun setUpToolbar() {
        if (toolbarVisible()) setSupportActionBar(toolbar)
    }

    private fun initUIMode() {
        delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
    }

}
