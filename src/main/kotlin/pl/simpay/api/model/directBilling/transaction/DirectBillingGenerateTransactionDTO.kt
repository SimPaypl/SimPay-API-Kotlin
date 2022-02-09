package pl.simpay.api.model.directBilling.transaction

data class DirectBillingGenerateTransactionDTO(
    val transactionId: String,
    val redirectUrl: String
)
