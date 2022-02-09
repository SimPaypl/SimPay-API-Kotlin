package pl.simpay.api.model.directBilling.transaction

data class Values(
    val net: Double,
    val gross: Double,
    val partner: Double
)
