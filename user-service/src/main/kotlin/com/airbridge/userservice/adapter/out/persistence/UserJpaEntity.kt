package com.airbridge.userservice.adapter.out.persistence

import com.airbridge.userservice.domain.model.User
import com.airbridge.userservice.domain.model.UserRole
import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator
import java.util.UUID

@Entity
@Table(name = "users")
data class UserJpaEntity(
    @Id
    @GeneratedValue
    @UuidGenerator
    var id: UUID? = null,

    @Column(nullable = false, unique = true)
    var email: String,

    @Column(nullable = false)
    var password: String,

    @Column(nullable = false)
    var name: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var role: UserRole = UserRole.USER
) {
    fun toDomain(): User = User(
        id = id,
        email = email,
        password = password,
        name = name,
        role = role
    )

    companion object {
        fun fromDomain(user: User): UserJpaEntity = UserJpaEntity(
            id = user.id,
            email = user.email,
            password = user.password,
            name = user.name,
            role = user.role
        )
    }
}

