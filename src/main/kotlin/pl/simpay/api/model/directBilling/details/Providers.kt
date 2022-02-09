package pl.simpay.api.model.directBilling.details

import com.squareup.moshi.Json

data class Providers(
    @Json(name = "t-mobile") val tMobile: Boolean,
    val orange: Boolean,
    val play: Boolean,
    val plus: Boolean
)
