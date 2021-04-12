package com.kazakago.blueprint.domain.model.global.state

sealed class State<out T>(
    val content: StateContent<T>
) {
    class Fixed<out T>(content: StateContent<T>) : State<T>(content)
    class Loading<out T>(content: StateContent<T>) : State<T>(content)
    class Error<out T>(content: StateContent<T>, val exception: Exception) : State<T>(content)
}