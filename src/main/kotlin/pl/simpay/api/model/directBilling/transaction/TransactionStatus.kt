package pl.simpay.api.model.directBilling.transaction

import pl.simpay.api.exception.UnsupportedStatusTypeException

enum class TransactionStatus(val statusName: String) {
    NEW("transaction_db_new"),
    PAYED("transaction_db_payed"),
    CONFIRMED("transaction_db_confirmed"),
    REJECTED("transaction_db_rejected"),
    CANCELED("transaction_db_canceled"),
    ERROR("transaction_db_generate_error");


    companion object {
        fun getByStatusName(name: String): TransactionStatus {
            return values().firstOrNull { it.statusName.equals(name, ignoreCase = true) }
                ?: throw UnsupportedStatusTypeException(name)
        }
    }
}
