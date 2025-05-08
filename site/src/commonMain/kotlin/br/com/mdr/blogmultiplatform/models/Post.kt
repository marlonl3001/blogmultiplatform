package br.com.mdr.blogmultiplatform.models

expect class Post {
    val id: String
    val author: String
    val date: String
    val title: String
    val subtitle: String
    val thumbnail: String
    val content: String
    val category: String
    val popular: Boolean
    val main: Boolean
    val sponsored: Boolean
}