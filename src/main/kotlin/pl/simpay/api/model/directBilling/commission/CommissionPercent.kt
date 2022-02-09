package pl.simpay.api.model.directBilling.commission

import com.squareup.moshi.Json

data class CommissionPercent(
    @Json(name = "commission_0") val commission0: String,
    @Json(name = "commission_9") val commission9: String,
    @Json(name = "commission_25") val commission25: String
)
