package pl.simpay.api.util

import com.google.common.hash.Hashing
import java.nio.charset.StandardCharsets

internal fun String.hashToSha256(): String {
    return Hashing.sha256().hashString(this, StandardCharsets.UTF_8).toString()
}
