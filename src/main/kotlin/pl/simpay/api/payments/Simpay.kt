package pl.simpay.api.payments

import pl.simpay.api.service.RestService

class Simpay(apikey: String, apiPassword: String, hashingKey: String = "") {

    val sms: Sms
    val smsXml: SmsXml
    val directBilling: DirectBilling

    init {
        val restService = RestService(apikey, apiPassword)
        sms = Sms(restService)
        smsXml = SmsXml(hashingKey)
        directBilling = DirectBilling(restService)
    }
}
