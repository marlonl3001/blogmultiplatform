package br.com.mdr.blogmultiplatform.util

import br.com.mdr.blogmultiplatform.models.User
import br.com.mdr.blogmultiplatform.models.UserResponse
import com.varabyte.kobweb.browser.api
import kotlinx.browser.window
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

suspend fun checkUserExistence(user: User): UserResponse? {
    return try {
        window.api.tryPost(
            apiPath = "usercheck",
            body = Json.encodeToString(user).encodeToByteArray()
        )?.decodeToString().parseData()
    } catch (e: Exception) {
        println("CURRENT_USER")
        println(e.message)
        null
    }
}

suspend fun checkUserId(id: String): Boolean {
    return try {
        window.api.tryPost(
            apiPath = "checkuserid",
            body = Json.encodeToString(id).encodeToByteArray()
        )?.decodeToString().parseData()
    } catch (e: Exception) {
        println("USER_ID")
        println(e.message)
        false
    }
}

inline fun <reified T> String?.parseData(): T {
    return Json.decodeFromString(this.toString())
}
