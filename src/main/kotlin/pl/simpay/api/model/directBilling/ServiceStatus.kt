package pl.simpay.api.model.directBilling

import pl.simpay.api.exception.UnsupportedStatusTypeException

enum class ServiceStatus(val statusName: String) {
    NEW("service_db_new"),
    ACTIVE("service_active"),
    REJECTED("service_db_rejected"),
    ONGOING_REGISTRATION("service_db_ongoing_registration");

    companion object {
        fun getByName(name: String): ServiceStatus {
            return values().firstOrNull { it.statusName.equals(name, ignoreCase = true) }
                ?: throw UnsupportedStatusTypeException(name)
        }
    }
}
