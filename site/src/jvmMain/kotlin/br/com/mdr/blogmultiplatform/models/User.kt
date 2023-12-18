package br.com.mdr.blogmultiplatform.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId

@Serializable
actual data class User(
    @SerialName(value = "_id")
    @Contextual actual val id: String = ObjectId.get().toHexString(),
    actual val userName: String,
    actual val password: String
)

@Serializable
actual data class UserResponse(
    @SerialName(value = "_id")
    @Contextual actual val id: String = ObjectId.get().toHexString(),
    actual val userName: String
)
