package model.db.request

import model.generic.Operator

data class DbGenerateRequest(var serviceId: String) {
    lateinit var control: String
    lateinit var complete: String
    lateinit var failure: String
    var amount: String? = null
    var amount_gross: String? = null
    var amount_required: String? = null
    lateinit var provider: Operator
    lateinit var sign: String
}
