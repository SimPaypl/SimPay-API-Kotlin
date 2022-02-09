package pl.simpay.api.model.directBilling.commission

import com.squareup.moshi.Json

data class CommissionsValuesDTO(
    val orange: CommissionValue,
    val play: CommissionValue,
    @Json(name = "t-mobile") val tMobile: CommissionValue,
    val plus: CommissionValue
)
