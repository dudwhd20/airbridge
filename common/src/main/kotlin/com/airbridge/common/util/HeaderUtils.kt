package com.airbridge.common.util

object HeaderUtils {
    fun getAuthorization(headers: Map<String, String>): String? =
        headers["Authorization"]
}
