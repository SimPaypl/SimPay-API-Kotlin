package pl.simpay.api.model.directBilling.commission

import com.squareup.moshi.Json

data class CommissionsPercents(
    @Json(name = "t-mobile") val tMobile: CommissionPercent,
    val orange: CommissionPercent,
    val play: CommissionPercent,
    val plus: CommissionPercent
)
