package br.com.mdr.blogmultiplatform.data

import br.com.mdr.blogmultiplatform.models.Post
import br.com.mdr.blogmultiplatform.models.User

interface MongoRepository {
    suspend fun checkUserExistence(user: User): User?
    suspend fun addPost(post: Post): Boolean
    suspend fun updatePost(post: Post): Boolean
}