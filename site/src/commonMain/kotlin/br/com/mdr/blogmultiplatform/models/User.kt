package br.com.mdr.blogmultiplatform.models

expect class User {
    val id: String
    val userName: String
    val password: String
}

expect class UserResponse {
    val id: String
    val userName: String
}
