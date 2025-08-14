package com.airbridge.userservice.application.port.out

import com.airbridge.userservice.domain.model.User

interface SaveUserPort {
    fun save(user: User): User
}

