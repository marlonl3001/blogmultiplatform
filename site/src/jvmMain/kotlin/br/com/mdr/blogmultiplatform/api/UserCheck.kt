package br.com.mdr.blogmultiplatform.api

import br.com.mdr.blogmultiplatform.data.MongoDB
import br.com.mdr.blogmultiplatform.models.User
import br.com.mdr.blogmultiplatform.models.UserResponse
import br.com.mdr.blogmultiplatform.utils.Constants.CHECK_USER_API_PATH
import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.data.getValue
import com.varabyte.kobweb.api.http.setBodyText
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.nio.charset.StandardCharsets
import java.security.MessageDigest

@Api(routeOverride = CHECK_USER_API_PATH)
suspend fun userCheck(context: ApiContext) {
    try {
        val request = context.req.body?.decodeToString()?.let {
            Json.decodeFromString<User>(it)
        }
        val user = request?.let {
            context.data.getValue<MongoDB>().checkUserExistence(
                User(userName = it.userName, password = hashPassword(it.password))
            )
        }
        if (user != null) {
            context.res.setBodyText(
                Json.encodeToString(UserResponse(id = user.id, userName = user.userName))
            )
        } else {
            context.res.setBodyText(
                Json.encodeToString(
                    Exception("User doesn't exist.")
                )
            )
        }
    } catch (e: Exception) {
        context.res.setBodyText(
            Json.encodeToString(
                Exception(e.message)
            )
        )
    }
}

private fun hashPassword(password: String): String {
    val messageDigest = MessageDigest.getInstance("SHA-256")
    val hashBytes = messageDigest.digest(password.toByteArray(StandardCharsets.UTF_8))
    val hexString = StringBuffer()

    for (byte in hashBytes) {
        // %02x is used to format each byte of the hash value as two digit hexadecimal number
        hexString.append(String.format("%02x", byte))
    }

    return hexString.toString()
}
