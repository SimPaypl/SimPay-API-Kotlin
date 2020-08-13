package model.db.request

data class DbTransactionRequest(
    var id: String,
    var key: String,
    var secret: String
)

