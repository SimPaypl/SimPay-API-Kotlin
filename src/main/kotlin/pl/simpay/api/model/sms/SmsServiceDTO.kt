package pl.simpay.api.model.sms

import com.squareup.moshi.Json
import java.time.LocalDateTime

data class SmsServiceDTO(
    val id: Int,
    val type: SmsServiceType,
    val status: ServiceStatus,
    val name: String,
    val prefix: String,
    val suffix: String,
    val adult: Boolean,
    @Json(name = "created_at") val createdAt: LocalDateTime
)
