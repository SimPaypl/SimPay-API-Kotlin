package pl.simpay.api.model.directBilling.details

import com.squareup.moshi.Json

data class OperatorMaxValues(
    @Json(name = "t-mobile") val tMobile: String, val orange: String, val play: String, val plus: String
)
