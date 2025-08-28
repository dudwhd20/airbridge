package com.airbridge.userservice.application.service

import com.airbridge.userservice.application.port.`in`.RegisterUserCommand
import com.airbridge.userservice.application.port.`in`.RegisterUserUseCase
import com.airbridge.userservice.application.port.out.SaveUserPort
import com.airbridge.userservice.domain.model.User
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserCommandService(
    private val saveUserPort: SaveUserPort
) : RegisterUserUseCase {
    override fun register(command: RegisterUserCommand): UUID {
        val user = User(
            email = command.email,
            password = command.password,
            name = command.name
        )
        val saved = saveUserPort.save(user)
        return requireNotNull(saved.id) { "Saved user id must not be null" }
    }
}
