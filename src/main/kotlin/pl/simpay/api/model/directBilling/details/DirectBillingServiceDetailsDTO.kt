package pl.simpay.api.model.directBilling.details

import com.squareup.moshi.Json
import pl.simpay.api.model.directBilling.ServiceStatus
import pl.simpay.api.model.directBilling.commission.CommissionsPercents
import java.time.LocalDateTime

data class DirectBillingServiceDetailsDTO(
    val id: Int,
    val name: String,
    val suffix: String,
    val status: ServiceStatus,
    val api: Api,
    val providers: Providers,
    @Json(name = "commissions") val commissionsPercents: CommissionsPercents,
    val maxValues: OperatorMaxValues,
    @Json(name = "created_at") val createdAt: LocalDateTime
)
