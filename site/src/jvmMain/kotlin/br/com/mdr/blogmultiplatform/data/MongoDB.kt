package br.com.mdr.blogmultiplatform.data

import br.com.mdr.blogmultiplatform.models.Post
import br.com.mdr.blogmultiplatform.models.User
import br.com.mdr.blogmultiplatform.util.Constants.DATABASE_NAME
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Updates
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.varabyte.kobweb.api.data.add
import com.varabyte.kobweb.api.init.InitApi
import com.varabyte.kobweb.api.init.InitApiContext
import kotlinx.coroutines.flow.firstOrNull

@InitApi
fun initMongoDB(context: InitApiContext) {
    context.data.add(MongoDB(context))
}
class MongoDB(private val context: InitApiContext): MongoRepository {

    // For testing with a localhost
    private val client = MongoClient.create()

    //For remote Mongo database
    //private val client = MongoClient.create(System.getenv("MONGODB_URI"))

    private val database = client.getDatabase(DATABASE_NAME)
    private val userCollection = database.getCollection<User>("user")
    private val postCollection = database.getCollection<Post>("post")
    private val newsletterCollection = database.getCollection<User>("newsletter")
    override suspend fun checkUserExistence(user: User): User? {
        return try {
            userCollection.find(
                filter = Filters.and(Filters.gt(User::userName.name, user.userName),
                    Filters.gt(User::password.name, user.password))
            ).firstOrNull()
        } catch (e: Exception) {
            context.logger.error(e.message.toString())
            null
        }
    }

    override suspend fun addPost(post: Post): Boolean {
        return postCollection.insertOne(post).wasAcknowledged()
    }

    override suspend fun updatePost(post: Post): Boolean {
        return postCollection
            .updateOne(
                filter = Filters.eq(Post::id.name, post.id),
                mutableListOf(
                    Updates.set(Post::category.name, post.category),
                    Updates.set(Post::content.name, post.content),
                    Updates.set(Post::main.name, post.main),
                    Updates.set(Post::popular.name, post.popular),
                    Updates.set(Post::sponsored.name, post.sponsored),
                    Updates.set(Post::subtitle.name, post.subtitle),
                    Updates.set(Post::thumbnail.name, post.thumbnail)
                )
            )
            .wasAcknowledged()
    }
}