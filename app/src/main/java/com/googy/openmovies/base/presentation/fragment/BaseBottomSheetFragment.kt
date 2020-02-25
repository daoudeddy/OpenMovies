package com.googy.openmovies.base.presentation.fragment

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.googy.openmovies.R
import com.googy.openmovies.base.presentation.adapter.BaseAdapter
import com.googy.openmovies.base.presentation.extension.observe
import com.googy.openmovies.base.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.launch


abstract class BaseBottomSheetFragment<BaseState : Any, C : BaseViewModel.BaseCommand, VM : BaseViewModel<BaseState, C>> : BottomSheetDialogFragment() {

    abstract fun initUi(view: View)
    abstract val viewModel: VM
    abstract val layoutId: Int

    internal val adapter: BaseAdapter by lazy { BaseAdapter() }

    open fun onViewModelCommand(command: C) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(
            STYLE_NORMAL,
            if (isNightMode()) R.style.AppTheme_BottomSheet_Dark else R.style.AppTheme_BottomSheet_Light
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(layoutId, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.commandLiveData.observe(this, ::onViewModelCommand)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch { initUi(view) }
    }



    private fun isNightMode(): Boolean {
        return resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
    }

}