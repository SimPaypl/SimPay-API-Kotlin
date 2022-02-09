package pl.simpay.api.model.pagination

import com.squareup.moshi.Json

data class Links(@Json(name = "next_page") val nextPage: String?, @Json(name = "prev_page") val prevPage: String?)
