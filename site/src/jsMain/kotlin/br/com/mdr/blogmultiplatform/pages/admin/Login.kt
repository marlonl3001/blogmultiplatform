package br.com.mdr.blogmultiplatform.pages.admin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import br.com.mdr.blogmultiplatform.models.Theme
import br.com.mdr.blogmultiplatform.models.User
import br.com.mdr.blogmultiplatform.models.UserResponse
import br.com.mdr.blogmultiplatform.navigation.Screen
import br.com.mdr.blogmultiplatform.styles.LoginInputStyle
import br.com.mdr.blogmultiplatform.util.Constants.FONT_FAMILY
import br.com.mdr.blogmultiplatform.util.Constants.LOGIN_INPUT_HEIGHT
import br.com.mdr.blogmultiplatform.util.Constants.LOGIN_INPUT_H_PADDING
import br.com.mdr.blogmultiplatform.util.Constants.LOGIN_INPUT_WIDTH
import br.com.mdr.blogmultiplatform.util.Constants.SMALL_BORDER_RADIUS
import br.com.mdr.blogmultiplatform.util.Constants.SMALL_MARGIN
import br.com.mdr.blogmultiplatform.util.Ids
import br.com.mdr.blogmultiplatform.util.Res
import br.com.mdr.blogmultiplatform.util.checkUserExistence
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontSize
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.Visibility
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.modifiers.visibility
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.components.text.SpanText
import kotlinx.browser.document
import kotlinx.browser.localStorage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Input
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.set

@Page
@Composable
fun LoginScreen() {
    var errorText by remember { mutableStateOf(" ") }
    val context = rememberPageContext()
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(leftRight = 50.px, top = 80.px, bottom = 24.px)
                .backgroundColor(Theme.LightGray.rgb),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .margin(bottom = 50.px)
                    .width(100.px),
                src = Res.Image.logo,
                description = "Image logo"
            )
            Input(
                type = InputType.Text,
                attrs = LoginInputStyle
                    .toModifier()
                    .id(Ids.userNameInput)
                    .margin(bottom = SMALL_MARGIN.px)
                    .width(LOGIN_INPUT_WIDTH.px)
                    .height(LOGIN_INPUT_HEIGHT.px)
                    .fontFamily(FONT_FAMILY)
                    .padding(leftRight = LOGIN_INPUT_H_PADDING.px)
                    .backgroundColor(Colors.White)
                    .borderRadius(r = SMALL_BORDER_RADIUS.px)
                    .toAttrs {
                        attr(
                            "placeholder",
                            "Username"
                        )
                    }
            )
            Input(
                type = InputType.Password,
                attrs = LoginInputStyle
                    .toModifier()
                    .id(Ids.passwordInput)
                    .margin(bottom = SMALL_MARGIN.px)
                    .width(LOGIN_INPUT_WIDTH.px)
                    .height(LOGIN_INPUT_HEIGHT.px)
                    .fontFamily(FONT_FAMILY)
                    .padding(leftRight = LOGIN_INPUT_H_PADDING.px)
                    .backgroundColor(Colors.White)
                    .borderRadius(r = SMALL_BORDER_RADIUS.px)
                    .toAttrs {
                        attr(
                            "placeholder",
                            "Password"
                        )
                    }
            )
            Button(
                attrs = Modifier
                    .width(LOGIN_INPUT_WIDTH.px)
                    .height(LOGIN_INPUT_HEIGHT.px)
                    .backgroundColor(Theme.Primary.rgb)
                    .color(Colors.White)
                    .borderRadius(SMALL_BORDER_RADIUS.px)
                    .fontFamily(FONT_FAMILY)
                    .fontWeight(FontWeight.Medium)
                    .fontSize(FontSize.Medium)
                    .border(
                        width = 0.px,
                        style = LineStyle.None,
                        color = Colors.Transparent
                    )
                    .cursor(Cursor.Pointer)
                    .onClick {
                        scope.launch {
                            val userName = (document.getElementById(Ids.userNameInput) as HTMLInputElement).value
                            val password = (document.getElementById(Ids.passwordInput) as HTMLInputElement).value
                            if (userName.isNotEmpty() && password.isNotEmpty()) {
                                val user = checkUserExistence(
                                    user = User(
                                        username = userName,
                                        password = password
                                    )
                                )

                                if (user != null) {//User exists in MongoDB
                                    rememberUserLogged(
                                        remember = true,
                                        response = user
                                    )
                                    context.router.navigateTo(Screen.Home.route)
                                } else {
                                    errorText = "User not found!"
                                    delay(3000)
                                    errorText = ""
                                }
                            } else {
                                errorText = "Input fields are empty."
                                delay(3000)
                                errorText = ""
                            }
                        }
                    }
                    .toAttrs()
            ) {
                SpanText(text = "Sign In")
            }
            SpanText(
                modifier = Modifier
                    .width(LOGIN_INPUT_WIDTH.px)
                    .color(Colors.Red)
                    .textAlign(TextAlign.Center)
                    .fontFamily(FONT_FAMILY)
                    .visibility(
                        if (errorText.isEmpty())
                            Visibility.Hidden
                        else
                            Visibility.Visible
                    ),
                text = errorText
            )
        }
    }
}

private fun rememberUserLogged(
    remember: Boolean,
    response: UserResponse? = null
) {
    localStorage["remember"] = remember.toString()
    if (response != null) {
        localStorage["userId"] = response._id
        localStorage["username"] = response.username
    }
}
