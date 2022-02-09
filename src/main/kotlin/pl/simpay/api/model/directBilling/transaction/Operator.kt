package pl.simpay.api.model.directBilling.transaction

import pl.simpay.api.exception.UnsupportedOperatorException

enum class Operator(val operatorName: String) {
    ORANGE("Orange"),
    PLUS("Plus"),
    PLAY("Play"),
    T_MOBILE("T-mobile");

    companion object {
        fun getByOperatorName(name: String): Operator {
            return values().firstOrNull { it.operatorName.equals(name, ignoreCase = true) }
                ?: throw UnsupportedOperatorException(name)
        }
    }

}
