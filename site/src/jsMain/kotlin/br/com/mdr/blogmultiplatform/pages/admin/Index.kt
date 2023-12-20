package br.com.mdr.blogmultiplatform.pages.admin

import androidx.compose.runtime.Composable
import br.com.mdr.blogmultiplatform.util.isUserLoggedIn
import com.varabyte.kobweb.core.Page
import org.jetbrains.compose.web.dom.Text

@Page
@Composable
fun HomePage() {
    isUserLoggedIn {
        HomeScreen()
    }
}

@Composable
fun HomeScreen() {
    Text("WELCOME HOME!")
}
