package model.sms.response

data class CodeVerifyResponse(val status: String, val test: Int,
                              val from: Int, val number: Int,
                              val code: String, val time_used: Long, val value: Double)
