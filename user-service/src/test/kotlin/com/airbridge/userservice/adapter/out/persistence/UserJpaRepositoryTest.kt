package com.airbridge.userservice.adapter.out.persistence

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.test.context.ActiveProfiles

@DataJpaTest
@ActiveProfiles("test")
class UserJpaRepositoryTest {

    @Autowired
    lateinit var userJpaRepository: UserJpaRepository

    @Test
    fun `사용자를 저장하고 이메일로 조회할 수 있다`() {
        val entity = UserJpaEntity(
            email = "repo@example.com",
            password = "pw",
            name = "repo"
        )
        val saved = userJpaRepository.save(entity)
        assertThat(saved.id).isNotNull()

        val found = userJpaRepository.findByEmail("repo@example.com")
        assertThat(found).isNotNull()
        assertThat(found!!.id).isEqualTo(saved.id)
    }

    @Test
    fun `이메일은 유니크해야 한다`() {
        val first = UserJpaEntity(
            email = "dup@example.com",
            password = "pw1",
            name = "dup1"
        )
        userJpaRepository.saveAndFlush(first)

        val second = UserJpaEntity(
            email = "dup@example.com",
            password = "pw2",
            name = "dup2"
        )
        assertThrows<DataIntegrityViolationException> {
            userJpaRepository.saveAndFlush(second)
        }
    }
}

