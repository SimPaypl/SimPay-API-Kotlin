package pl.simpay.api.payments

import pl.simpay.api.util.hashToSha256
import java.text.Normalizer
import java.util.concurrent.ThreadLocalRandom

private const val CHARSET = "ABCDEFGHIJKLMNPQRSTUVWXYZ123456789"
private const val SIGN = "sign"
private const val SMS_ID = "sms_id"
private const val SMS_FROM = "sms_from"
private const val SMS_TEXT = "sms_text"
private const val SEND_NUMBER = "send_number"
private const val SEND_TIME = "send_time"

class SmsXml(private val hashingKey: String) {

    private val codes: Map<String, Double> = object : HashMap<String, Double>() {
        init {
            put("7055", 0.25)
            put("7136", 0.5)
            put("7255", 1.0)
            put("7355", 1.5)
            put("7455", 2.0)
            put("7555", 2.5)
            put("7636", 3.0)
            put("77464", 3.5)
            put("78464", 4.0)
            put("7936", 4.5)
            put("91055", 5.0)
            put("91155", 5.5)
            put("91455", 7.0)
            put("91664", 8.0)
            put("91955", 9.5)
            put("92055", 10.0)
            put("92555", 12.5)
        }
    }

    fun checkParameters(map: Map<String, Any>): Boolean {
        val params = listOf(SEND_NUMBER, SMS_TEXT, SMS_FROM, SMS_ID, SIGN)
        params.forEach {
            if (it !in map) {
                return false
            }
        }

        return map[SIGN] == sign(map)
    }

    fun generateCode(): String {
        val length = 6

        val builder = StringBuilder()

        for (i in 0..length) {
            builder.append(CHARSET[ThreadLocalRandom.current().nextInt(0, CHARSET.length)])
        }
        return builder.toString()
    }

    fun getSmsValue(number: String): Double? {
        return codes[number]
    }

    fun generateXml(text: String): String {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><sms-response>${
            Normalizer.normalize(
                text,
                Normalizer.Form.NFKD
            )
        }<sms-text></sms-text></sms-response>"
    }

    private fun sign(map: Map<String, Any>): String {
        return (map[SMS_ID].toString() + map[SMS_TEXT] + map[SMS_FROM] + map[SEND_NUMBER] + map[SEND_TIME] + hashingKey).hashToSha256()
    }
}
