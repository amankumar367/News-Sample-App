package com.news.app.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData

@Composable
inline fun <T, E : Any> RequestStateRender(
    liveData: LiveData<RequestState<T, E>>,
    onSuccess: @Composable (T) -> Unit,
    onError: @Composable ((E) -> Unit) = { },
    onLoading: @Composable (() -> Unit) = { }
) {
    val state = liveData.observeAsState(initial = RequestState.Idle)
    RequestStateRender(state = state, onSuccess, onError, onLoading)
}

@Composable
inline fun <T, E : Any> RequestStateRender(
    state: State<RequestState<T, E>>,
    onSuccess: @Composable (T) -> Unit,
    onError: @Composable ((E) -> Unit) = { },
    onLoading: @Composable (() -> Unit) = { }
) {
    when (val itemValue = state.value) {
        is RequestState.Success -> onSuccess.invoke(itemValue.value)
        is RequestState.Idle -> {  /* Do nothing */ }
        is RequestState.Error -> onError.invoke(itemValue.value)
        RequestState.Loading -> onLoading.invoke()
    }
}

/**
 * A State that represents request progress of a single value
 */
sealed class RequestState<out V, out E : Any> {
    object Idle : RequestState<Nothing, Nothing>()
    object Loading : RequestState<Nothing, Nothing>()
    data class Success<out SV>(val value: SV) : RequestState<SV, Nothing>()
    data class Error<out EV : Any>(val value: EV) : RequestState<Nothing, EV>()
}

