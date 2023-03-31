package pl.simpay.api.service

import okhttp3.Interceptor
import okhttp3.Response

private const val SIM_KEY_HEADER = "X-SIM-KEY"
private const val SIM_PASSWORD_HEADER = "X-SIM-PASSWORD"
private const val SIM_VERSION_HEADER = "X-SIM-VERSION"
private const val SIM_PLATFORM_HEADER = "X-SIM-PLATFORM"

class AuthInterceptor(private val apiKey: String, private val simPassword: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .header(SIM_KEY_HEADER, apiKey)
            .header(SIM_PASSWORD_HEADER, simPassword)
            .header(SIM_VERSION_HEADER, "2.1.1")
            .header(SIM_PLATFORM_HEADER, "KOTLIN")
            .build()
        return chain.proceed(request)
    }
}
