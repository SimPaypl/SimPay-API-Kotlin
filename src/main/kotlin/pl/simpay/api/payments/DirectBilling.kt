package pl.simpay.api.payments

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import pl.simpay.api.adapter.TransactionStatusAdapter
import pl.simpay.api.model.directBilling.DirectBillingServiceDTO
import pl.simpay.api.model.directBilling.commission.CommissionsValuesDTO
import pl.simpay.api.model.directBilling.details.DirectBillingServiceDetailsDTO
import pl.simpay.api.model.directBilling.transaction.DirectBillingGenerateTransactionDTO
import pl.simpay.api.model.directBilling.transaction.DirectBillingTransactionDetailsDTO
import pl.simpay.api.model.directBilling.transaction.DirectBillingTransactionNotificationDTO
import pl.simpay.api.model.directBilling.transaction.DirectBillingTransactionsDTO
import pl.simpay.api.model.request.GenerateTransactionRequest
import pl.simpay.api.model.response.PaginatedResponse
import pl.simpay.api.model.response.Response
import pl.simpay.api.service.RestService
import pl.simpay.api.util.hashToSha256

private const val DEFAULT_PAGE = 1
private const val DEFAULT_LIMIT = 15

class DirectBilling(private val restService: RestService) {

    fun getServiceList(page: Int = DEFAULT_PAGE, limit: Int = DEFAULT_LIMIT): PaginatedResponse<Set<DirectBillingServiceDTO>>? {
        val endpoint = "/directbilling?page=%d&limit=%d".format(page, limit)
        val parameterizedType = Types.newParameterizedType(
            PaginatedResponse::class.java,
            Types.newParameterizedType(MutableSet::class.java, DirectBillingServiceDTO::class.java)
        )
        return restService.sendGetRequest(
            endpoint,
            parameterizedType
        )
    }

    fun getServiceDetails(serviceId: Int): Response<DirectBillingServiceDetailsDTO?>? {
        val endpoint = "/directbilling/%d".format(serviceId)
        val parameterizedType = Types.newParameterizedType(
            Response::class.java,
            DirectBillingServiceDetailsDTO::class.java
        )
        return restService.sendGetRequest(endpoint, parameterizedType) as Response<DirectBillingServiceDetailsDTO?>?
    }

    fun calculateCommission(serviceId: Int, amount: Double): Response<CommissionsValuesDTO>? {
        val endpoint = "/directbilling/%d/calculate?amount=%f".format(serviceId, amount)
        val parameterizedType = Types.newParameterizedType(
            Response::class.java,
            CommissionsValuesDTO::class.java
        )
        return restService.sendGetRequest(endpoint, parameterizedType)
    }

    fun getTransactions(
        serviceId: Int,
        page: Int = DEFAULT_PAGE,
        limit: Int = DEFAULT_LIMIT
    ): PaginatedResponse<Set<DirectBillingTransactionsDTO>>? {
        val endpoint = "/directbilling/%d/transactions?page=%d&limit=%d".format(serviceId, page, limit)
        val parameterizedType = Types.newParameterizedType(
            PaginatedResponse::class.java, Types.newParameterizedType(
                MutableSet::class.java,
                DirectBillingTransactionsDTO::class.java
            )
        )
        return restService.sendGetRequest(
            endpoint,
            parameterizedType
        )
    }

    fun getTransactionDetails(serviceId: Int, transactionId: String): Response<DirectBillingTransactionDetailsDTO>? {
        val endpoint = "/directbilling/%d/transactions/%s".format(serviceId, transactionId)
        val parameterizedType = Types.newParameterizedType(
            Response::class.java,
            DirectBillingTransactionDetailsDTO::class.java
        )
        return restService.sendGetRequest(endpoint, parameterizedType)
    }

    fun generateTransaction(
        serviceId: Int,
        request: GenerateTransactionRequest
    ): Response<DirectBillingGenerateTransactionDTO>? {
        val endpoint = "/directbilling/%d/transactions".format(serviceId)
        val parameterizedType = Types.newParameterizedType(
            Response::class.java,
            DirectBillingGenerateTransactionDTO::class.java
        )
        return restService.sendPostRequest(
            endpoint, request, parameterizedType
        )
    }

    fun checkSignature(key: String, transactionJson: String): Boolean {
        val moshi = Moshi.Builder().add(TransactionStatusAdapter()).build()
        val transactionNotification =
            moshi.adapter(DirectBillingTransactionNotificationDTO::class.java).fromJson(transactionJson)
        return transactionNotification != null && generateSignature(
            key,
            transactionNotification
        ) == transactionNotification.signature
    }

    private fun generateSignature(key: String, notification: DirectBillingTransactionNotificationDTO): String {
        return listOf(
            notification.id.toString(),
            notification.status.statusName,
            notification.values.net.toString(),
            notification.values.gross.toString(),
            notification.values.partner.toString(),
            notification.returns.success,
            notification.returns.failure,
            notification.control,
            notification.numberFrom,
            notification.provider.toString(),
            notification.signature,
            key
        ).joinToString("|").hashToSha256()
    }
}
