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
        // User 도메인에 id가 없다면, 저장 후 id를 반환하는 방식으로 확장 필요
        // 임시로 UUID.nameUUIDFromBytes(email+name) 사용
        return UUID.nameUUIDFromBytes((saved.email + saved.name).toByteArray())
    }
}
