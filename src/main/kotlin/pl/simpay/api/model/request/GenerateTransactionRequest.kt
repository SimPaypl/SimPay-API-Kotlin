package pl.simpay.api.model.request

import pl.simpay.api.util.hashToSha256

data class GenerateTransactionRequest(
    val amount: Double,
    val amountType: String?,
    val description: String?,
    val control: String?,
    val returns: RedirectURL?,
    val phoneNumber: String?,
    var signature: String = "",
) {

    fun sign(key: String) {
        this.signature = listOf(
            amount.toString(),
            amountType,
            description,
            control,
            returns!!.success,
            returns.failure,
            phoneNumber,
            key
        ).joinToString("|").hashToSha256()
    }

    fun signWithAmountAndControl(key: String) {
        this.signature = listOf(amount.toString(), control, key).joinToString("|").hashToSha256()
    }
}
