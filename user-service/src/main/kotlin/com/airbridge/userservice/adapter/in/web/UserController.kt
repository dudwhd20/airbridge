package com.airbridge.userservice.adapter.`in`.web

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController {
    // 회원가입 API 예시
    @PostMapping("/register")
    fun register(@RequestBody request: RegisterUserRequest): String {
        // 실제 구현은 서비스 호출로 대체
        return "회원가입 요청 처리됨"
    }
}

// 회원가입 요청 DTO 예시
data class RegisterUserRequest(
    val email: String,
    val password: String,
    val name: String
)
