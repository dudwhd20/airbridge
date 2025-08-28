package com.airbridge.userservice.domain.model

import java.util.UUID

data class User(
    val id: UUID? = null,
    val email: String,
    val password: String,
    val name: String,
    val role: UserRole = UserRole.USER
)

enum class UserRole {
    USER, ADMIN
}
