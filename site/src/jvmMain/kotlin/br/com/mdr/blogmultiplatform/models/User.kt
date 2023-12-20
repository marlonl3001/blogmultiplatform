package br.com.mdr.blogmultiplatform.models

import kotlinx.serialization.Serializable
import org.bson.codecs.ObjectIdGenerator

@Serializable
actual data class User(
    actual val _id: String = ObjectIdGenerator().generate().toString(),
    actual val username: String = "",
    actual val password: String = ""
)

@Serializable
actual data class UserResponse(
    actual val _id: String = ObjectIdGenerator().generate().toString(),
    actual val username: String = ""
)
