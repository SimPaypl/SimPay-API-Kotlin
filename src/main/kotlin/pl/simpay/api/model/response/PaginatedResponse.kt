package pl.simpay.api.model.response

import pl.simpay.api.model.pagination.Pagination

data class PaginatedResponse<T>(val success: Boolean, val data: T, val pagination: Pagination, val errors: Map<String, List<String>>?)
