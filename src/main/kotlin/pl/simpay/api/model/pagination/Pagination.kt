package pl.simpay.api.model.pagination

import com.squareup.moshi.Json

data class Pagination(
    val total: Int,
    val count: Int,
    @Json(name = "next_page") val nextPage: String?,
    @Json(name = "total_pages") val totalPages: String?,
    @Json(name = "current_page") val currentPage: String?,
    @Json(name = "prev_page") val prevPage: String?,
    val links: Links
)
