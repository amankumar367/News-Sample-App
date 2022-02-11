package com.news.app.extensions

import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.SimpleDateFormat
import java.util.*
import javax.net.ssl.SSLHandshakeException


fun Throwable.transform(): Exception {
    return when (this) {
        is UnknownHostException,
        is InterruptedIOException,
        is ConnectException -> Exception("You're currently offline. Please check your network connection and try again.")
        is SSLHandshakeException,
        is SocketTimeoutException -> Exception("We are unable to connect to our servers. Please check your connection and try again.")
        is EmptyResultSetException -> this
        else -> Exception("Something went wrong please try again.")
    }
}

class EmptyResultSetException(override val message: String = "No data found") : Exception()

fun returnNoDataException(details: String = "No data found"): Nothing {
    throw EmptyResultSetException(message = details)
}

fun getCurrentDate(): String {
    val c: Date = Calendar.getInstance().time
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return simpleDateFormat.format(c)
}

fun String.formatDate(): String {
    return if (isNotBlank())
        split("T")[0]
    else
        ""
}