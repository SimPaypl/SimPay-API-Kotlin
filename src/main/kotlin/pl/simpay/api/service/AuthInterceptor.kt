package pl.simpay.api.service

import okhttp3.Interceptor
import okhttp3.Response

private const val SIM_KEY_HEADER = "X-SIM-KEY"
private const val SIM_PASSWORD_HEADER = "X-SIM-PASSWORD"

class AuthInterceptor(private val apiKey: String, private val simPassword: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .header(SIM_KEY_HEADER, apiKey)
            .header(SIM_PASSWORD_HEADER, simPassword)
            .build()
        return chain.proceed(request)
    }
}
