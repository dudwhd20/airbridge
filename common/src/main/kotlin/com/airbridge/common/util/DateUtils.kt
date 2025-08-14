package com.airbridge.common.util

import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

object DateUtils {
    fun toDate(localDateTime: LocalDateTime): Date =
        Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant())

    fun toLocalDateTime(date: Date): LocalDateTime =
        date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
}
