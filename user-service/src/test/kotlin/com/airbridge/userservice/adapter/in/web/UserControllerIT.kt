package com.airbridge.userservice.adapter.`in`.web

import com.airbridge.userservice.adapter.out.persistence.UserJpaRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class UserControllerIT {

    @Autowired lateinit var mockMvc: MockMvc
    @Autowired lateinit var objectMapper: ObjectMapper
    @Autowired lateinit var userJpaRepository: UserJpaRepository

    @Test
    fun register_endpoint_persists_and_returns_201() {
        val request = RegisterUserRequest(
            email = "it-controller@example.com",
            password = "pw",
            name = "ctrl"
        )

        val result = mockMvc.post("/api/users/register") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(request)
        }.andExpect {
            status { isCreated() }
        }.andReturn()

        val body = result.response.contentAsString
        assertThat(body).contains("userId")
        assertThat(userJpaRepository.findByEmail("it-controller@example.com")).isNotNull()
    }
}

