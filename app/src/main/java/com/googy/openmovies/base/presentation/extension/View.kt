package com.googy.openmovies.base.presentation.extension

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.SearchView

fun View.show() {
    if (visibility != View.VISIBLE) visibility = View.VISIBLE
}

fun View.hide() {
    if (visibility != View.GONE) visibility = View.GONE
}

fun TextView.showOrHide(text: String) {
    if (text.isEmpty()) hide()
    else show()
}

fun TextView.showOrHide(show: Boolean) {
    if (!show) hide()
    else show()
}

fun SearchView.setOnQueryTextListener(onEmailChange: (String) -> Unit) {
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean {
            return false
        }

        override fun onQueryTextChange(newText: String): Boolean {
            onEmailChange(newText)
            return true
        }
    })
}
