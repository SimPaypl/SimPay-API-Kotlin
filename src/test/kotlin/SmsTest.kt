import model.sms.request.CodeVerifyRequest
import model.sms.request.ServiceListRequest
import payments.Sms

object SmsTest {
    private const val API_KEY = "XXXXXXXX"
    private const val API_SECRET = "XXXXXXXXXXXXXXXXXXXXX"
    private const val SERVICE_ID = "XXXX"

    @JvmStatic
    fun main(args: Array<String>) {
        verifyCode()
        getServiceList()
    }

    private fun verifyCode() {
        val sms = Sms()

        val request = CodeVerifyRequest(API_KEY, API_SECRET, SERVICE_ID)
        request.number = "XXXX"
        request.code = "XXXXXXX"

        println(sms.verifyCode(request))
    }

    private fun getServiceList() {
        val sms = Sms()

        val request = ServiceListRequest(API_KEY, API_SECRET)

        println(sms.getServiceList(request))
    }
}
