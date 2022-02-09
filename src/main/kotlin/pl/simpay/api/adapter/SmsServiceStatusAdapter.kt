package pl.simpay.api.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import pl.simpay.api.model.sms.ServiceStatus

class SmsServiceStatusAdapter: Adapter<ServiceStatus> {

    @ToJson
    override fun toJson(type: ServiceStatus): String {
        return type.statusName
    }

    @FromJson
    override fun fromJson(jsonVariable: String): ServiceStatus {
        return ServiceStatus.getByName(jsonVariable)
    }
}
