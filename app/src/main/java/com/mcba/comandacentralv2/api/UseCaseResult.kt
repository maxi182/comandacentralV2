package com.mcba.comandacentralv2.api

sealed class UseCaseResult<out T : Any> {
    class Success<out T : Any>(val data: T) : UseCaseResult<T>()
    class Failure<out T : Any>(val data: T) : UseCaseResult<T>()
    class Exception(val exception: Throwable) : UseCaseResult<Nothing>()
}