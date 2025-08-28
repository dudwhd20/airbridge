package com.airbridge.userservice.application.service

import com.airbridge.userservice.application.port.`in`.RegisterUserCommand
import com.airbridge.userservice.adapter.out.persistence.UserJpaRepository
import com.airbridge.userservice.application.port.`in`.RegisterUserUseCase
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class UserCommandServiceIT {

    @Autowired
    lateinit var useCase: RegisterUserUseCase


    @Autowired
    lateinit var userJpaRepository: UserJpaRepository

    @Test
    fun register_persists_user_and_returns_id() {
        val cmd = RegisterUserCommand(
            email = "it-service@example.com",
            password = "pw",
            name = "svc"
        )
        val id = useCase.register(cmd)
        assertThat(id).isNotNull()

        val entity = userJpaRepository.findByEmail("it-service@example.com")
        assertThat(entity).isNotNull()
        assertThat(entity!!.id).isEqualTo(id)
    }
}

