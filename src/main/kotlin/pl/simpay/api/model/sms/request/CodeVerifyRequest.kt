package model.sms.request

data class CodeVerifyRequest(
    var key: String,
    var secret: String,
    var service_id: String
) {
    lateinit var number: String
    lateinit var code: String
}
