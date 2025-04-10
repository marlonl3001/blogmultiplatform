package br.com.mdr.blogmultiplatform.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
actual data class Post(
    @SerialName("_id") //Unique identifier for MongoDB
    actual val id: String = "",
    actual val author: String,
    actual val date: String,
    actual val title: String,
    actual val subtitle: String,
    actual val thumbnail: String,
    actual val content: String,
    actual val category: String,
    actual val popular: Boolean,
    actual val main: Boolean,
    actual val sponsored: Boolean
)
