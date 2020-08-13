package payments

import model.generic.ApiResponse
import model.generic.ParametrizedRequest
import model.sms.request.CodeVerifyRequest
import model.sms.request.ServiceListRequest
import model.sms.response.CodeVerifyResponse
import model.sms.response.ServicesResponse
import utils.sendPost

private const val VERIFY_CODE_URL = "https://simpay.pl/api/status"
private const val SERVICE_LIST_URL = "https://simpay.pl/api/get_services"

class Sms {

    // https://docs.simpay.pl/#weryfikacja-kodu
    fun verifyCode(request: CodeVerifyRequest): ApiResponse<CodeVerifyResponse> {
        return sendPost(VERIFY_CODE_URL, ParametrizedRequest(request), ApiResponse())
    }

    // https://docs.simpay.pl/#pobieranie-listy-uslug
    fun getServiceList(request: ServiceListRequest): ApiResponse<ServicesResponse> {
        return sendPost(SERVICE_LIST_URL, ParametrizedRequest(request), ApiResponse())
    }
}
