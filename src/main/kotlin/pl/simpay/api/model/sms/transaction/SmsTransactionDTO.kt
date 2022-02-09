package pl.simpay.api.model.sms.transaction

import com.squareup.moshi.Json
import java.time.LocalDateTime

data class SmsTransactionDTO(
    val id: Int,
    val from: Long,
    val code: String,
    val used: Boolean,
    @Json(name = "send_at") val sendAt: LocalDateTime
)
