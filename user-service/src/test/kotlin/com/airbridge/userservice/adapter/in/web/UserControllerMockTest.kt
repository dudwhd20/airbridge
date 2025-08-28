package com.airbridge.userservice.adapter.`in`.web

import com.airbridge.userservice.application.port.`in`.RegisterUserUseCase
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.Profile
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import java.util.UUID

@WebMvcTest(UserController::class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test", "web-mock")
@Import(UserControllerMockTest.MockConfig::class)
class UserControllerMockTest {

    @TestConfiguration
    @Profile("web-mock")
    class MockConfig {
        @Bean
        fun registerUserUseCase(): RegisterUserUseCase = mock()
    }

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var registerUserUseCase: RegisterUserUseCase

    @Test
    fun `회원가입이 성공하면 201을 반환한다`() {
        val userId = UUID.randomUUID()

        val request = RegisterUserRequest(
            email = "test@example.com",
            password = "securepass123",
            name = "테스트유저"
        )

        // given
        given(registerUserUseCase.register(any())).willReturn(userId)

        // when & then
        mockMvc.post("/api/users/register") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(request)
        }
            .andExpect {
                status { isCreated() }
                jsonPath("$.userId") { value(userId.toString()) }
            }
    }
}
