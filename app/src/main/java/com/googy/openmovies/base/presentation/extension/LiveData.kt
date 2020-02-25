package com.googy.openmovies.base.presentation.extension

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observe(
    baseFragment: Fragment,
    block: (T) -> Unit
) {
    observe(baseFragment, Observer(block))
}

fun <T> LiveData<T>.observes(
    baseFragment: Fragment,
    block: (T) -> Unit
) {
    observe(baseFragment, Observer(block))
}

fun <T> LiveData<T>.removeObserver(
    block: (T) -> Unit
) {
    removeObserver(Observer(block))
}
