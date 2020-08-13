import model.db.request.DbGenerateRequest
import model.db.request.DbServiceCommissionRequest
import model.db.request.DbServicesListRequest
import model.db.request.DbTransactionLimitsRequest
import payments.DirectBilling

object DirectBillingTest {
    private const val API_KEY = "XXXXXXXXXXXXXXXXX"
    private const val API_SECRET = "XXXXXXXXXXXXXXXXXXXXXXX"
    private const val SERVICE_ID = "XXXXX"

    @JvmStatic
    fun main(args: Array<String>) {
        generateTransaction()
        transactionLimits()
        serviceCommission()
        serversIp()
        services()
    }

    private fun serversIp() {
        val db = DirectBilling()

        println(db.getServersIp())
    }

    private fun transactionLimits() {
        val db = DirectBilling()

        val request = DbTransactionLimitsRequest(SERVICE_ID)
        request.key = API_KEY
        request.secret = API_SECRET

        println(db.getTransactionLimits(request))
    }

    private fun services() {
        val db = DirectBilling()

        val request = DbServicesListRequest()
        request.key = API_KEY
        request.secret = API_SECRET

        println(db.getServices(request))
    }

    private fun serviceCommission() {
        val db = DirectBilling()

        val request = DbServiceCommissionRequest(SERVICE_ID)
        request.key = API_KEY
        request.secret = API_SECRET

        println(db.getServiceCommission(request))
    }

    private fun generateTransaction() {
        val db = DirectBilling()

        val request = DbGenerateRequest(SERVICE_ID)
        request.control = "XXXXXXX"
        request.amount = "12.50"

        println(db.generateTransaction(API_KEY, request))
    }
}
