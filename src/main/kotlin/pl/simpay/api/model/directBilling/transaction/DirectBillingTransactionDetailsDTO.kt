package pl.simpay.api.model.directBilling.transaction

import com.squareup.moshi.Json
import java.time.LocalDateTime

data class DirectBillingTransactionDetailsDTO(
    val id: Int,
    val status: TransactionStatus,
    val phoneNumber: String?,
    val value: Double,
    @Json(name = "value_netto") val netValue: Double,
    val operator: Operator,
    @Json(name = "notify") val notification: Notification,
    @Json(name = "created_at") val createdAt: LocalDateTime,
    @Json(name = "updated_at") val updatedAt: LocalDateTime
)
