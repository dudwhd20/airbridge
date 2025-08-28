package com.airbridge.userservice.adapter.out.persistence

import com.airbridge.userservice.domain.model.User
import com.airbridge.userservice.application.port.out.SaveUserPort
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(
    private val userJpaRepository: UserJpaRepository
) : SaveUserPort {
    override fun save(user: User): User {
        val entity = UserJpaEntity.fromDomain(user)
        val saved = userJpaRepository.save(entity)
        return saved.toDomain()
    }
}
