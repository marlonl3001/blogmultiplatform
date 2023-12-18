package br.com.mdr.blogmultiplatform.models

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
@Serializable
data class Post(
    @BsonId val id: String = ObjectId.get().toHexString(),
    val category: String,
    val content: String,
    val main: Boolean = true,
    val popular: Boolean = false,
    val sponsored: Boolean = false,
    val subtitle: String,
    val thumbnail: String
)
