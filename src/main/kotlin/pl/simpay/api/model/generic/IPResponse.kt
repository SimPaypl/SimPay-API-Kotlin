package model.generic

data class IPResponse(
    val status: String="",
    val ips: List<String> = emptyList())

