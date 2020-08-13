package model.sms.response

data class Service(val id: Number, val sufix: String,
                   val numbers: List<String>, val status: String)
