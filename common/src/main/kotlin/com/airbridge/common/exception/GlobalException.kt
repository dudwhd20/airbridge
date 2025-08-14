package com.airbridge.common.exception

class GlobalException(
    val errorCode: ErrorCode,
    override val message: String = errorCode.message
) : RuntimeException(message)
