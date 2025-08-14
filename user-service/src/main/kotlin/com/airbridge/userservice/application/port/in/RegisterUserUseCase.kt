package com.airbridge.userservice.application.port.`in`

import com.airbridge.userservice.domain.model.User

interface RegisterUserUseCase {
    fun register(command: RegisterUserCommand): User
}

data class RegisterUserCommand(
    val email: String,
    val password: String,
    val name: String
)

