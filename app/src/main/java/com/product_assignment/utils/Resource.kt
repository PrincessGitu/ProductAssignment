package com.product_assignment.utils

/**
 * Created by Gitanjali Ghangale on 10/25/2021.
 */
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T?):Resource<T>(data)
    class Error<T>(message: String?,data: T?=null):Resource<T>(data,message)
    class Loading<T>:Resource<T>()
}