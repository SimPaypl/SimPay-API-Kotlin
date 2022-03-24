package pl.simpay.api.model.directBilling.transaction

import com.squareup.moshi.Json
import pl.simpay.api.model.request.RedirectURL

data class DirectBillingTransactionNotificationDTO(
    val id: String,
    @Json(name = "service_id") val serviceId: Int,
    val status: TransactionStatus,
    val values: Values,
    val returns: RedirectURL,
    val control: String,
    @Json(name = "number_from") val numberFrom: String,
    val provider: Int,
    val signature: String
)
