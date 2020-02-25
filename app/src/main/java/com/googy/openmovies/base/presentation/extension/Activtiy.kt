package com.googy.openmovies.base.presentation.extension

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import com.googy.openmovies.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

inline fun <reified F : Fragment> FragmentActivity.addFragment(
    backStack: Boolean = false,
    replace: Boolean = false,
    vararg params: Pair<String, Any>
): F {
    val fragment = newFragment<F>(*params)
    supportFragmentManager.beginTransaction().apply {
        replace(R.id.container, fragment, F::class.java.canonicalName)
        if (backStack) addToBackStack(F::class.java.canonicalName)
        commit()
    }
    return fragment
}

fun FragmentActivity.openBottomSheet(
    dialogFragment: DialogFragment,
    tag: String = UUID.randomUUID().toString()
) {
    run {
        lifecycleScope.launchWhenStarted {
            dialogFragment.show(supportFragmentManager.beginTransaction().addToBackStack(tag), tag)
        }
    }
}

fun FragmentActivity.addFragment(
    clazz: Class<*>,
    backStack: Boolean = false,
    vararg params: Pair<String, Any>
) {
    supportFragmentManager.beginTransaction().apply {
        replace(R.id.container, newFragment(clazz, *params), clazz.canonicalName)
        if (backStack) addToBackStack(clazz.canonicalName)
        commit()
    }
}

fun FragmentActivity.addFragment(
    fragment: Fragment,
    backStack: Boolean = false,
    vararg params: Pair<String, Any>
) {
    supportFragmentManager.beginTransaction().apply {
        replace(R.id.container, fragment, fragment::class.java.canonicalName)
        if (backStack) addToBackStack(fragment::class.java.canonicalName)
        commit()
    }
}

fun FragmentActivity.show(
    fragment: Fragment
) {
    supportFragmentManager.beginTransaction().apply {
        show(fragment)
        commit()
    }
}

inline fun <reified T> FragmentActivity.intent(key: String, default: T) = lazy {
    val value = (intent?.extras?.get(key) ?: default)
    return@lazy when {
        default is Boolean && value is String -> {
            value.toBoolean() as T
        }
        else -> {
            value as T
        }
    }
}

fun <T> FragmentActivity.throttleLatest(
    destinationFunction: (T) -> Unit
): (T) -> Unit {
    var throttleJob: Job? = null
    var latestParam: T
    return { param: T ->
        latestParam = param
        if (throttleJob?.isCompleted != false) {
            throttleJob = lifecycleScope.launch {
                delay(300L)
                latestParam.let(destinationFunction)
            }
        }
    }
}
