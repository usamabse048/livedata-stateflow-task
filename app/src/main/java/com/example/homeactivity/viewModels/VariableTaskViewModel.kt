package com.example.homeactivity.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class VariableTaskViewModel : ViewModel() {

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _age = MutableLiveData(0)
    val age: LiveData<Int> = _age

    private var _variableTaskButtonState =
        MutableStateFlow<VariableTaskButtonState>(
          VariableTaskButtonState.Empty
        )
    val variableTaskButtonState: StateFlow<VariableTaskButtonState> =
        _variableTaskButtonState

    fun changeName(value: String) {
        _name.value = value
        Log.d("msg: ", "This is the ${_name.value}")
    }

    fun changeEmail(value: String) {
        _email.value = value
    }

    fun changeAge(value: String) {
        if (value == "") {
            _age.value = -1
        } else {
            _age.value = value.toInt()
        }

    }

    fun changeButtonState() = viewModelScope.launch {
        Log.d("msg: ", "Check Null/Empty ${_name.value.isNullOrEmpty()}")
        if (!_name.value.isNullOrEmpty() && !_email.value.isNullOrEmpty() && _age.value!! >= 18) {
            _variableTaskButtonState.value = VariableTaskButtonState.Enable

        } else {
            _variableTaskButtonState.value = VariableTaskButtonState.Disable
        }
    }

    sealed class VariableTaskButtonState {
        object Empty : VariableTaskButtonState()
        object Enable : VariableTaskButtonState()
        object Disable : VariableTaskButtonState()

    }


}