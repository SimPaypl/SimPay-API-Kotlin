package pl.simpay.api.model.directBilling.transaction

import com.squareup.moshi.Json
import java.time.LocalDateTime

data class DirectBillingTransactionsDTO(
    val id: String,
    val status: TransactionStatus,
    val value: Double,
    @Json(name = "value_netto")
    val netValue: Double,
    val operator: Operator,
    @Json(name = "created_at")
    val createdAt: LocalDateTime,
    @Json(name = "updated_at")
    val updatedAt: LocalDateTime
)
