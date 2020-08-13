package utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import exception.ApiException
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.lang.reflect.Type

private const val CONTENT_TYPE_VALUE = "application/json"
private const val HTTP_OK_CODE = 200

private fun okHttp(): OkHttpClient {
    return OkHttpClient()
}

private fun gson(): Gson {
    return GsonBuilder().setPrettyPrinting().create()
}

internal fun sendPost(url: String, body: RequestBody): String {
    val builder = Request.Builder()
    val post = builder.url(url).post(body).build()

    val response = okHttp().newCall(post).execute()
    if (response.code != HTTP_OK_CODE)
        throw ApiException(response.message)

    return response.body!!.string().also { response.close() }
}

internal inline fun <reified RES> sendFormPost(url: String, request: RequestBody, response: RES): RES {
    return gson().fromJson(
        sendPost(
            url,
            request
        ), RES::class.java
    )
}

internal inline fun <REQ, reified RES> sendPost(url: String, request: REQ, response: RES): RES {
    return gson().fromJson(
        sendPost(
            url,
            gson().toJson(request).toRequestBody(CONTENT_TYPE_VALUE.toMediaTypeOrNull())
        ), RES::class.java
    )
}

internal fun sendGet(url: String): String {
    val builder = Request.Builder()
    val post = builder.url(url).get().build()

    val response = okHttp().newCall(post).execute()
    if (response.code != HTTP_OK_CODE)
        throw ApiException(response.message)

    return response.body!!.string().also { response.close() }
}

internal fun <T> sendGet(url: String, result: Any): T {
    return gson().fromJson(sendGet(url), object : TypeToken<T>() {}.type)
}

internal fun <T> sendGet(url: String, type: Type): T {
    return gson().fromJson(sendGet(url), type)
}