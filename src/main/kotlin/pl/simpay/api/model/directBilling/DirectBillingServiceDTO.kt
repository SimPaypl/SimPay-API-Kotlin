package pl.simpay.api.model.directBilling

import com.squareup.moshi.Json
import java.time.LocalDateTime

data class DirectBillingServiceDTO(
    val id: Int,
    val name: String,
    val suffix: String,
    val status: ServiceStatus,
    @Json(name = "created_at") val createdAt: LocalDateTime
)
