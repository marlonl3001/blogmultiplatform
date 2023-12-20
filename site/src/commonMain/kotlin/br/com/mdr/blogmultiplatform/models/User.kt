package br.com.mdr.blogmultiplatform.models

expect class User {
    val _id: String
    val username: String
    val password: String
}

expect class UserResponse {
    val _id: String
    val username: String
}
