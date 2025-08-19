package com.airbridge.userservice.adapter.`in`.web

import com.airbridge.userservice.application.port.`in`.RegisterUserCommand
import com.airbridge.userservice.application.port.`in`.RegisterUserUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api/users")
class UserController(
    private val registerUserUseCase: RegisterUserUseCase
) {
    @PostMapping("/register")
    fun registerUser(@RequestBody request: RegisterUserRequest): ResponseEntity<RegisterUserResponse> {
        val userId = registerUserUseCase.register(
            RegisterUserCommand(
                email = request.email,
                password = request.password,
                name = request.name
            )
        )
        return ResponseEntity.status(HttpStatus.CREATED).body(RegisterUserResponse(userId))
    }
}

data class RegisterUserRequest(
    val email: String,
    val password: String,
    val name: String
)

data class RegisterUserResponse(
    val userId: UUID
)

