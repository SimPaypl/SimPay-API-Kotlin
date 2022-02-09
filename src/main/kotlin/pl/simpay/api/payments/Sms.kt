package pl.simpay.api.payments

import com.squareup.moshi.Types
import pl.simpay.api.model.request.CodeVerifyRequest
import pl.simpay.api.model.response.PaginatedResponse
import pl.simpay.api.model.response.Response
import pl.simpay.api.model.sms.SmsServiceDTO
import pl.simpay.api.model.sms.code.CodeVerifyDTO
import pl.simpay.api.model.sms.details.SmsServiceDetailsDTO
import pl.simpay.api.model.sms.number.NumberDTO
import pl.simpay.api.model.sms.transaction.SmsTransactionDTO
import pl.simpay.api.model.sms.transaction.SmsTransactionDetailsDTO
import pl.simpay.api.service.RestService

private const val DEFAULT_PAGE = 1
private const val DEFAULT_LIMIT = 15

class Sms(private val restService: RestService) {

    fun getServiceList(page: Int = DEFAULT_PAGE, limit: Int = DEFAULT_LIMIT): PaginatedResponse<Set<SmsServiceDTO>>? {
        val endpoint = "/sms?page=%d&limit=%d".format(page, limit)
        val parameterizedType = Types.newParameterizedType(
            PaginatedResponse::class.java, Types.newParameterizedType(
                MutableSet::class.java,
                SmsServiceDTO::class.java
            )
        )
        return restService.sendGetRequest(endpoint, parameterizedType)
    }

    fun getServiceDetails(serviceId: Int): Response<SmsServiceDetailsDTO>? {
        val endpoint = "/sms/%d".format(serviceId)
        val parameterizedType = Types.newParameterizedType(
            Response::class.java,
            SmsServiceDetailsDTO::class.java
        )
        return restService.sendGetRequest(endpoint, parameterizedType)
    }

    fun getTransactions(
        serviceId: Int,
        page: Int = DEFAULT_PAGE,
        limit: Int = DEFAULT_LIMIT
    ): PaginatedResponse<Set<SmsTransactionDTO>>? {
        val endpoint = "/sms/%d/transactions?page=%d&limit=%d".format(serviceId, page, limit)
        val parameterizedType = Types.newParameterizedType(
            PaginatedResponse::class.java, Types.newParameterizedType(
                MutableSet::class.java,
                SmsTransactionDTO::class.java
            )
        )
        return restService.sendGetRequest(endpoint, parameterizedType)
    }

    fun getTransactionDetails(serviceId: Int, transactionId: Int): Response<SmsTransactionDetailsDTO>? {
        val endpoint = "/sms/%d/transactions/%d".format(serviceId, transactionId)
        val parameterizedType = Types.newParameterizedType(
            Response::class.java,
            SmsTransactionDetailsDTO::class.java
        )
        return restService.sendGetRequest(endpoint, parameterizedType)
    }

    fun getServiceNumbers(serviceId: Int, page: Int = DEFAULT_PAGE, limit: Int = DEFAULT_LIMIT): PaginatedResponse<Set<NumberDTO>>? {
        val endpoint = "/sms/%d/numbers?page=%d&limit=%d".format(serviceId, page, limit)
        val parameterizedType = Types.newParameterizedType(
            PaginatedResponse::class.java, Types.newParameterizedType(
                MutableSet::class.java,
                NumberDTO::class.java
            )
        )
        return restService.sendGetRequest(endpoint, parameterizedType)
    }

    fun getServiceNumbersDetails(serviceId: Int, number: Long): Response<NumberDTO>? {
        val endpoint = "/sms/%d/numbers/%d".format(serviceId, number)
        val parameterizedType = Types.newParameterizedType(
            Response::class.java,
            NumberDTO::class.java
        )
        return restService.sendGetRequest(endpoint, parameterizedType)
    }

    fun getNumbers(page: Int = DEFAULT_PAGE, limit: Int = DEFAULT_LIMIT): PaginatedResponse<Set<NumberDTO>>? {
        val endpoint = "/sms/numbers?page=%d&limit=%d".format(page, limit)
        val parameterizedType = Types.newParameterizedType(
            PaginatedResponse::class.java, Types.newParameterizedType(
                MutableSet::class.java,
                NumberDTO::class.java
            )
        )
        return restService.sendGetRequest(endpoint, parameterizedType)
    }

    fun getNumberDetails(number: Long): Response<NumberDTO>? {
        val endpoint = "/sms/numbers/%d".format(number)
        val parameterizedType = Types.newParameterizedType(
            Response::class.java,
            NumberDTO::class.java
        )
        return restService.sendGetRequest(endpoint, parameterizedType)
    }

    fun verifyCode(serviceId: Int, code: String, number: Long): Response<CodeVerifyDTO>? {
        val endpoint = "/sms/%d".format(serviceId)
        val parameterizedType = Types.newParameterizedType(
            Response::class.java,
            CodeVerifyDTO::class.java
        )
        return restService.sendPostRequest(
            endpoint, CodeVerifyRequest(code, number), parameterizedType
        )
    }
}
