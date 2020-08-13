package model.db.request

data class DbServiceCommissionRequest(var service_id: String) {
    lateinit var key: String
    lateinit var secret: String
}

