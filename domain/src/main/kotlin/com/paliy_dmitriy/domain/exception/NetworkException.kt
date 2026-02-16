package com.paliy_dmitriy.domain.exception

import java.io.IOException

open class NetworkException(message: String, cause: Throwable? = null) : IOException(message, cause)