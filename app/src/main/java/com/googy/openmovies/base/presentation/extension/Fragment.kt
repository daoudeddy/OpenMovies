package com.googy.openmovies.base.presentation.extension

import android.view.ContextThemeWrapper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.googy.openmovies.R
import java.util.*

inline fun <reified T : ViewModel> Fragment.viewModelFactory(): Lazy<T> {
    return lazy {
        ViewModelProvider(
            viewModelStore,
            ViewModelProvider.AndroidViewModelFactory.getInstance(activity!!.application)
        ).get(T::class.java)
    }
}

inline fun <reified F : Fragment> Fragment.addFragment(
    backStack: Boolean = false,
    vararg params: Pair<String, Any>
) {
    fragmentManager?.beginTransaction()?.apply {
        replace(R.id.container, newFragment<F>(*params), F::class.java.canonicalName)
        if (backStack) addToBackStack(F::class.java.canonicalName)
        commit()
    }
}


fun Fragment.openBottomSheet(
    dialogFragment: DialogFragment,
    tag: String = UUID.randomUUID().toString()
) {
    (activity as? AppCompatActivity)?.run {
        lifecycleScope.launchWhenStarted {
            dialogFragment.show(supportFragmentManager.beginTransaction().addToBackStack(tag), tag)
        }
    }
}

fun View.openBottomSheet(
    dialogFragment: DialogFragment,
    tag: String = UUID.randomUUID().toString()
) {
    ((context as? ContextThemeWrapper)?.baseContext as? AppCompatActivity)?.run {
        lifecycleScope.launchWhenStarted {
            dialogFragment.show(supportFragmentManager.beginTransaction().addToBackStack(tag), tag)
        }
    }
}


inline fun <reified T : Fragment> newFragment(vararg params: Pair<String, Any>): T {
    return T::class.java.newInstance().apply {
        arguments = bundleOf(*params)
    }
}

fun newFragment(clazz: Class<*>, vararg params: Pair<String, Any>): Fragment {
    return (clazz.newInstance() as Fragment).apply {
        arguments = bundleOf(*params)
    }
}

inline fun <reified T> Fragment.args(key: String, default: T) = lazy {
    (arguments?.get(key) ?: default) as T
}
