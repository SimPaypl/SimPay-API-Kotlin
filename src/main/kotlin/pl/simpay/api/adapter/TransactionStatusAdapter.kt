package pl.simpay.api.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import pl.simpay.api.model.directBilling.transaction.TransactionStatus

class TransactionStatusAdapter : Adapter<TransactionStatus> {

    @ToJson
    override fun toJson(type: TransactionStatus): String {
        return type.statusName
    }

    @FromJson
    override fun fromJson(jsonVariable: String): TransactionStatus {
        return TransactionStatus.getByStatusName(jsonVariable)
    }

}
