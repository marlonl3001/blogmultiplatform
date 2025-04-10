package br.com.mdr.blogmultiplatform.api

import br.com.mdr.blogmultiplatform.data.MongoDB
import br.com.mdr.blogmultiplatform.models.Post
import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.data.getValue
import com.varabyte.kobweb.api.http.setBodyText
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.bson.types.ObjectId

@Api(routeOverride = "addpost")
suspend fun addPost(context: ApiContext) {
    try {
        val postRequest = context.req.body?.decodeToString()?.let { Json.decodeFromString<Post>(it) }
        val post = postRequest?.copy(id = ObjectId.get().toHexString())
        context.res.setBodyText(
            Json.encodeToString(
                post?.let {
                    context.data.getValue<MongoDB>().addPost(it).toString()
                } ?: false.toString()
            )
        )
    } catch (e: Exception) {
        context.res.setBodyText(Json.encodeToString(e.message))
    }
}