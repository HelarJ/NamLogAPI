package com.jaadla.namlogapi.log

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Instant

@Entity
@Table(name = "messages")
class MessageEntity(
        @Id
        val id: Int? = null,
        val time: Instant? = null,
        val username: String? = null,
        val message: String? = null,
)