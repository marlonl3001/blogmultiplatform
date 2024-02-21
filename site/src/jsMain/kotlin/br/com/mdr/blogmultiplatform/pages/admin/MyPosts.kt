package br.com.mdr.blogmultiplatform.pages.admin

import androidx.compose.runtime.Composable
import br.com.mdr.blogmultiplatform.components.AdminPageLayout
import br.com.mdr.blogmultiplatform.util.isUserLoggedIn
import com.varabyte.kobweb.core.Page

@Page
@Composable
fun MyPostsPage() {
    isUserLoggedIn {
        MyPostsScreen()
    }
}

@Composable
fun MyPostsScreen() {
    AdminPageLayout {

    }
}
