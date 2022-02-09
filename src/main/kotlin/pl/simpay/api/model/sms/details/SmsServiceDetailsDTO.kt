package pl.simpay.api.model.sms.details

import com.squareup.moshi.Json
import pl.simpay.api.model.sms.ServiceStatus
import pl.simpay.api.model.sms.SmsServiceType
import java.time.LocalDateTime

data class SmsServiceDetailsDTO(
    val type: SmsServiceType,
    val id: Int,
    val status: ServiceStatus,
    val name: String,
    val prefix: String,
    val suffix: String,
    val adult: Boolean,
    val numbers: List<String>,
    @Json(name = "created_at") val createdAt: LocalDateTime

)
