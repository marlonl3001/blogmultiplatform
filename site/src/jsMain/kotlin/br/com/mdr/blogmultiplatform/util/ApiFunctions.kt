package br.com.mdr.blogmultiplatform.util

import br.com.mdr.blogmultiplatform.models.User
import br.com.mdr.blogmultiplatform.models.UserResponse
import br.com.mdr.blogmultiplatform.utils.Constants.CHECK_USER_API_PATH
import com.varabyte.kobweb.browser.api
import kotlinx.browser.window
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

suspend fun checkUserExistence(user: User): UserResponse? {
    return try {
        val result = window.api.tryPost(
            apiPath = CHECK_USER_API_PATH,
            body = Json.encodeToString(user).encodeToByteArray())
        Json.decodeFromString<UserResponse>(result.toString())
    } catch (e: Exception) {
        println(e.message)
        null
    }
}
