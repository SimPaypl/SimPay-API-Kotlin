package pl.simpay.api.model.response

data class Response<T>(val success: Boolean, val data: T, val errors: Map<String, List<String>>?)
