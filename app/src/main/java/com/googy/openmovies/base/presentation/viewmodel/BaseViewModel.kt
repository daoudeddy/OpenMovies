package com.googy.openmovies.base.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<ExtraState : Any, T : BaseViewModel.BaseCommand>(baseState: ExtraState) :
    ViewModel() {
    abstract val commandLiveData: MutableLiveData<T>
    private val stateLiveData: MutableLiveData<ExtraState> = MutableLiveData()

    init {
        stateLiveData.value = baseState
    }

    fun postCommand(command: T) {
        commandLiveData.postValue(command)
    }

    fun setState(block: ExtraState.() -> ExtraState) {
        stateLiveData.value = block(getState())
    }

    fun getState() = stateLiveData.value!!
    open class BaseCommand


}

