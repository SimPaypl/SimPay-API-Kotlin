package pl.simpay.api.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import pl.simpay.api.model.directBilling.ServiceStatus

class DirectBillingServiceStatusAdapter : Adapter<ServiceStatus> {

    @ToJson
    override fun toJson(type: ServiceStatus): String {
        return type.statusName
    }

    @FromJson
    override fun fromJson(jsonVariable: String): ServiceStatus {
        return ServiceStatus.getByName(jsonVariable)
    }
}
