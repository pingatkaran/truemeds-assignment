package com.app.truemeds.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.truemeds.repository.MainRepository
import com.app.truemeds.utils.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FetchDetailsViewModel @Inject constructor(val mainRepository: MainRepository) : ViewModel() {


    private val articaleStateFlow: MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)

    val articaledetails: StateFlow<ApiState> = articaleStateFlow

    fun getDetails() = viewModelScope.launch {
        articaleStateFlow.value = ApiState.Loading
        mainRepository.getDetails()
            .catch { e ->
                articaleStateFlow.value = ApiState.Failure(e)
            }.collect {
                articaleStateFlow.value = ApiState.Success(it)
            }
    }

}