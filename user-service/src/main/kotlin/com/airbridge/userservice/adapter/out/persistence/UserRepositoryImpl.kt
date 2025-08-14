package com.airbridge.userservice.adapter.out.persistence

import com.airbridge.userservice.domain.model.User
import com.airbridge.userservice.application.port.out.SaveUserPort
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl : SaveUserPort {
    override fun save(user: User): User {
        // JPA 저장소 연동 예시 (실제 구현 필요)
        return user
    }
}

