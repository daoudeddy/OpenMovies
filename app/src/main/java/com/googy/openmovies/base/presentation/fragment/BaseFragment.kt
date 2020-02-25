package com.googy.openmovies.base.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.googy.openmovies.base.presentation.adapter.BaseAdapter
import com.googy.openmovies.base.presentation.extension.observe
import com.googy.openmovies.base.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

abstract class BaseFragment<BaseState:Any,C : BaseViewModel.BaseCommand, VM : BaseViewModel<BaseState,C>> : Fragment() {

    abstract fun initUi(view: View)
    abstract val viewModel: VM
    abstract val layoutId: Int

    open val hideToolbar = false

    internal val adapter: BaseAdapter by lazy { BaseAdapter() }

    open fun onFragmentDestroyed() {}
    open fun onViewModelCommand(command: C) {}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(layoutId, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.commandLiveData.observe(this, ::onViewModelCommand)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch { initUi(view) }
    }

    override fun onResume() {
        super.onResume()
        checkToolbar()
    }

    private fun checkToolbar() {
        if (hideToolbar)
            (activity as? AppCompatActivity)?.supportActionBar?.hide()
        else
            (activity as? AppCompatActivity)?.supportActionBar?.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.commandLiveData.removeObserver(::onViewModelCommand)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onFragmentDestroyed()
    }
}

