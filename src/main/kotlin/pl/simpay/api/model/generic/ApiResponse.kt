package model.generic

data class ApiResponse<T>(var respond: T? = null, var error: Array<ApiError> = emptyArray()) {


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ApiResponse<*>

        if (respond != other.respond) return false
        if (!error.contentEquals(other.error)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = respond?.hashCode() ?: 0
        result = 31 * result + error.contentHashCode()
        return result
    }
}
