package pl.simpay.api.model.directBilling.commission

import com.squareup.moshi.Json

data class CommissionValue(
    @Json(name = "net") val netValue: Double, @Json(name = "gross") val grossValue: Double
)
