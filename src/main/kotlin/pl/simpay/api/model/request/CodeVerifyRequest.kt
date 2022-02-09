package pl.simpay.api.model.request

data class CodeVerifyRequest(val code: String, val number: Long)
