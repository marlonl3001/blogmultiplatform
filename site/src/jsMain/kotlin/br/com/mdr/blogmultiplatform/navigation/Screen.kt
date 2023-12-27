package br.com.mdr.blogmultiplatform.navigation

sealed class Screen(val route: String) {
    object Home : Screen(route = "/admin/")
    object Login: Screen(route = "/admin/login")
    object MyPosts: Screen(route = "/admin/myposts")
    object Post: Screen(route = "/admin/post")
    object Create: Screen(route = "/admin/create")
}