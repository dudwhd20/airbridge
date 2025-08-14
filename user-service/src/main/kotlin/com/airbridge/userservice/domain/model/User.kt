package com.airbridge.userservice.domain.model

data class User(
    val email: String,
    val password: String,
    val name: String,
    val role: UserRole = UserRole.USER
)

enum class UserRole {
    USER, ADMIN
}

