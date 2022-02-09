package pl.simpay.api.model.directBilling.transaction

import com.squareup.moshi.Json
import java.time.LocalDateTime

data class Notification(
    @Json(name = "is_send") val isSend: Boolean,
    @Json(name = "last_send_at") val lastSendAt: LocalDateTime,
    val count: Int
)
