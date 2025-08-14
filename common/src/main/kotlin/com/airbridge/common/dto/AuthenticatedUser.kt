package com.airbridge.common.dto

data class AuthenticatedUser(
    val userId: String,
    val role: String
)
