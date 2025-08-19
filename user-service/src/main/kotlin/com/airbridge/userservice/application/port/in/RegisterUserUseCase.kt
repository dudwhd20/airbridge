package com.airbridge.userservice.application.port.`in`

import java.util.UUID

interface RegisterUserUseCase {
    fun register(command: RegisterUserCommand): UUID
}

/**
 * 회원 가입 요청을 위한 커맨드
 */
data class RegisterUserCommand(
    val email: String,
    val password: String,
    val name: String
)

