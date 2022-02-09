package pl.simpay.api.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.LocalDateTime
import java.time.ZonedDateTime

class DateTimeAdapter : Adapter<LocalDateTime> {

    @ToJson
    override fun toJson(type: LocalDateTime): String {
        return type.toString()
    }

    @FromJson
    override fun fromJson(jsonVariable: String): LocalDateTime {
        return ZonedDateTime.parse(jsonVariable).toLocalDateTime()
    }

}
