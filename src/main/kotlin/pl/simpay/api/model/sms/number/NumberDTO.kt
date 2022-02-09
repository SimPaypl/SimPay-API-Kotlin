package pl.simpay.api.model.sms.number

import com.squareup.moshi.Json

data class NumberDTO(
    val number: Long,
    val value: Double,
    @Json(name = "value_gross") val valueGross: Double,
    val adult: Boolean
)
