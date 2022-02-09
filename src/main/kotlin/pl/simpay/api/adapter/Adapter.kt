package pl.simpay.api.adapter

interface Adapter<T> {

    fun toJson(type: T): String

    fun fromJson(jsonVariable: String): T
}
