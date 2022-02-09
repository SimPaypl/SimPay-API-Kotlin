package pl.simpay.api.service

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import pl.simpay.api.adapter.*
import java.lang.reflect.ParameterizedType

private const val BASE_URL = "https://api.simpay.pl"
private const val APPLICATION_JSON_MIME_TYPE = "application/json"

class RestService(simKey: String, simPassword: String) {

    private val moshi = Moshi.Builder().addAll(
        DateTimeAdapter(),
        OperatorAdapter(),
        TransactionStatusAdapter(),
        SmsServiceStatusAdapter(),
        DirectBillingServiceStatusAdapter()
    ).add(KotlinJsonAdapterFactory()).build()
    private val httpClient = OkHttpClient.Builder().addInterceptor(AuthInterceptor(simKey, simPassword)).build()

    internal fun <RES> sendGetRequest(endpoint: String, returnType: ParameterizedType): RES? {
        val request = Request.Builder().url("$BASE_URL/$endpoint").get().build()
        return moshi.adapter<RES>(returnType).fromJson(executeRequest(request))
    }

    internal inline fun <reified REQ, RES> sendPostRequest(
        endpoint: String,
        requestBody: REQ,
        returnType: ParameterizedType
    ): RES? {
        val toJson = moshi.adapter(REQ::class.java).toJson(requestBody)
        val post = Request.Builder().url("$BASE_URL/$endpoint")
            .post(toJson.toRequestBody(APPLICATION_JSON_MIME_TYPE.toMediaType())).build()

        return moshi.adapter<RES>(returnType).fromJson(executeRequest(post))
    }

    private fun executeRequest(request: Request): String {
        val response = httpClient.newCall(request).execute()
        return response.body!!.string().also { response.close() }
    }
}

private fun Moshi.Builder.addAll(vararg adapters: Adapter<*>): Moshi.Builder {
    adapters.forEach { this.add(it) }
    return this
}
