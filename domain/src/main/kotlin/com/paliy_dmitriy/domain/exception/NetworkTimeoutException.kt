package com.paliy_dmitriy.domain.exception

class NetworkUnreachableException(message: String, cause: Throwable? = null) : NetworkException(message, cause)