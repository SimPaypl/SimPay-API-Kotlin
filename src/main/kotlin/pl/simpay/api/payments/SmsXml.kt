package payments

import model.generic.ApiResponse
import model.generic.IPResponse
import utils.normalizeToNFKD
import utils.sendGet
import utils.toSha256
import kotlin.random.Random

private const val GET_IP_URL = "https://simpay.pl/api/get_ip"
private const val CHARSET = "ABCDEFGHIJKLMNPQRSTUVWXYZ123456789"
private const val SIGN = "sign"
private const val SMS_ID = "sms_id"
private const val SMS_FROM = "sms_from"
private const val SMS_TEXT = "sms_text"
private const val SEND_NUMBER = "send_number"
private const val SEND_TIME = "send_time"
private const val ZERO = 0

class SmsXml(private val apiKey: String) {
    private var codes: MutableMap<String, Double> = mutableMapOf()
    private val params = arrayOf("send_number", "sms_text", "sms_from", "sms_id", "sign")

    init {
        codes["7055"] = 0.25
        codes["7136"] = 0.5
        codes["7255"] = 1.0
        codes["7355"] = 1.5
        codes["7455"] = 2.0
        codes["7555"] = 2.5
        codes["7636"] = 3.0
        codes["77464"] = 3.5
        codes["78464"] = 4.0
        codes["7936"] = 4.5
        codes["91055"] = 5.0
        codes["91155"] = 5.5
        codes["91455"] = 7.0
        codes["91664"] = 8.0
        codes["91955"] = 9.5
        codes["92055"] = 10.0
        codes["92555"] = 12.5
    }

    // https://docs.simpay.pl/#odbieranie-informacji-o-sms
    fun checkParameters(map: Map<String, Any>): Boolean {
        params.forEach {
            if (map.containsKey(it)) {
                return false
            }
        }

        return map[SIGN] == sign(map)
    }

    // https://docs.simpay.pl/#odbieranie-informacji-o-sms
    fun generateCode(): String {
        val length = 6

        val builder = StringBuilder()

        for (i in ZERO..length) {
            builder.append(CHARSET[Random.nextInt(ZERO, CHARSET.length)])
        }
        return builder.toString()
    }

    // https://docs.simpay.pl/#odbieranie-informacji-o-sms
    fun getSmsValue(number: String): Double? {
        return codes[number]
    }

    // https://docs.simpay.pl/#odbieranie-informacji-o-sms
    fun generateXml(text: String): String {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><sms-response>${text.normalizeToNFKD()}<sms-text></sms-text></sms-response>"
    }

    fun getServersIp(ip: String): List<String> {
        val ipResponse :ApiResponse<IPResponse> = sendGet(GET_IP_URL, ApiResponse<IPResponse>())
        return ipResponse.respond!!.ips
    }

    private fun sign(map: Map<String, Any>): String {
        return "${map[SMS_ID].toString() + map[SMS_TEXT] + map[SMS_FROM] + map[SEND_NUMBER] + map[SEND_TIME]}$apiKey".toSha256()
    }
}
