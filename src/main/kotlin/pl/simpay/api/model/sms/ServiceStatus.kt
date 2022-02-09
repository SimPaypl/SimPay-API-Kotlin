package pl.simpay.api.model.sms

import pl.simpay.api.exception.UnsupportedStatusTypeException

enum class ServiceStatus(val statusName: String) {
    NEW("service_new"),
    ACTIVE("service_active"),
    BLOCKED("service_blocked"),
    DELETED("service_deleted"),
    SECOND_VERIFY("service_second_verify"),
    REJECTED("service_rejected"),
    VERIFY("service_verify"),
    ONGOING_REGISTRATION("service_ongoing_registration");

    companion object {
        fun getByName(name: String): ServiceStatus {
            return values().firstOrNull { it.statusName.equals(name, ignoreCase = true) }
                ?: throw UnsupportedStatusTypeException(name)
        }
    }

}
