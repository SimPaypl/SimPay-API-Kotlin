package payments

import com.google.gson.reflect.TypeToken
import model.db.DbCommission
import model.db.DbTransaction
import model.db.DbTransactionLimit
import model.db.request.*
import model.db.response.DbGenerateResponse
import model.db.response.DbServicesListResponse
import model.generic.ApiResponse
import model.generic.IPResponse
import model.generic.ParametrizedRequest
import okhttp3.FormBody
import utils.*

private const val API_URL = "https://simpay.pl/db/api"
private const val TRANSACTION_STATUS_URL = "https://simpay.pl/api/db_status"
private const val SERVICES_LIST_URL = "https://simpay.pl/api/get_services_db"
private const val TRANSACTION_LIMITS_URL = "https://simpay.pl/api/db_hosts"
private const val SERVICE_COMMISSION_URL = "https://simpay.pl/api/db_hosts_commission"
private const val GET_IP_URL = "https://simpay.pl/api/get_ip"
private const val COMMA = ","
private const val DOT = "."


class DirectBilling {

    // https://docs.simpay.pl/#generowanie-transakcji
    fun generateTransaction(apiKey: String, request: DbGenerateRequest): DbGenerateResponse {
        val amount = request.amount ?: request.amount_gross ?: request.amount_required!!

        val builder = FormBody.Builder()

        request.sign =
                (request.serviceId + amount.toDouble().formatTwoDigitAfterComma().replace(COMMA,
                                                                                                  DOT) + request.control + apiKey).toSha256()
        for (map in request.serialize()) {
            println(map)
            builder.add(map.key, map.value)
        }

        return sendFormPost(API_URL, builder.build(), DbGenerateResponse())
    }

    // https://docs.simpay.pl/#pobieranie-danych-o-transakcji
    fun getTransaction(request: DbTransactionRequest): ApiResponse<DbTransaction> {
        return sendPost(TRANSACTION_STATUS_URL, ParametrizedRequest(request), ApiResponse())
    }

    fun getServices(request: DbServicesListRequest): ApiResponse<DbServicesListResponse> {
        return sendPost(SERVICES_LIST_URL, ParametrizedRequest(request), ApiResponse())
    }

    // https://docs.simpay.pl/#pobieranie-maksymalnych-kwot-transakcji
    fun getTransactionLimits(request: DbTransactionLimitsRequest): ApiResponse<List<DbTransactionLimit>> {
        return sendPost(TRANSACTION_LIMITS_URL, ParametrizedRequest(request), ApiResponse())
    }

    // https://docs.simpay.pl/#lista-ip-serwerow-simpay
    fun getServersIp(): List<String> {
        val response: ApiResponse<IPResponse> = sendGet(GET_IP_URL, object : TypeToken<ApiResponse<IPResponse>>() {}.type)
        return response.respond!!.ips
    }

    // https://docs.simpay.pl/#pobieranie-prowizji-dla-uslugi
    fun getServiceCommission(request: DbServiceCommissionRequest): ApiResponse<List<DbCommission>> {
        return sendPost(SERVICE_COMMISSION_URL, ParametrizedRequest(request), ApiResponse())
    }

    // https://docs.simpay.pl/#odbieranie-transakcji
    fun sign(id: Int, status: String, valuenet: String, valuepartner: String, control: String, apiKey: String): String {
        return (id.toString() + status + valuenet + valuepartner + control + apiKey).toSha256()
    }
}
