package com.paliy_dmitriy.domain.exception

class NetworkTimeoutException(message: String, cause: Throwable? = null) : NetworkException(message, cause)