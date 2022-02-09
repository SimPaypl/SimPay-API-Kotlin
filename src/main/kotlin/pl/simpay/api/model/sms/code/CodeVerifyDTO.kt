package pl.simpay.api.model.sms.code

import com.squareup.moshi.Json
import java.time.LocalDateTime

data class CodeVerifyDTO(
    val used: Boolean,
    val code: String,
    val test: Boolean,
    val from: Int,
    val number: Long,
    val value: Double,
    @Json(name = "used_at") val usedAt: LocalDateTime?
)
