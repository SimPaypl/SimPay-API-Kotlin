package model.db.request

data class DbTransactionLimitsRequest(var service_id :String) {
    lateinit var key: String
    lateinit var secret: String
}
