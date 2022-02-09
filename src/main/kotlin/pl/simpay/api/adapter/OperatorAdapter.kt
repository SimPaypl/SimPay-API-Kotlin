package pl.simpay.api.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import pl.simpay.api.model.directBilling.transaction.Operator

class OperatorAdapter: Adapter<Operator> {

    @ToJson
    override fun toJson(type: Operator): String {
        return type.operatorName
    }

    @FromJson
    override fun fromJson(jsonVariable: String): Operator {
        return Operator.getByOperatorName(jsonVariable)
    }
}
