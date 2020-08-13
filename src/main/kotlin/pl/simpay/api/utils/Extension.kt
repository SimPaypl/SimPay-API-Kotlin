@file:Suppress("NO_REFLECTION_IN_CLASS_PATH")

package utils

import com.google.common.hash.Hashing
import java.nio.charset.StandardCharsets
import java.text.DecimalFormat
import java.text.Normalizer

internal fun String.toSha256(): String {
    return Hashing.sha256().hashString(this, StandardCharsets.UTF_8).toString()
}

internal fun String.normalizeToNFKD(): String {
    return Normalizer.normalize(this, Normalizer.Form.NFKD)
}

internal fun Double.formatTwoDigitAfterComma(): String {
    return DecimalFormat("0.##").format(this)
}

internal fun Any.serialize(): HashMap<String, String> {
    val map = HashMap<String, String>()

    for (member in this::class.members) {
        if (member.javaClass.name.contains("Function"))
            continue

        try {
            val call = member.call(this)

            if (call === null) continue

            map[member.name] = call.toString()
        } catch (ignored: Exception) {}

    }

    return map
}