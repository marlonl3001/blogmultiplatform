package br.com.mdr.blogmultiplatform.pages.admin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import br.com.mdr.blogmultiplatform.models.Theme
import br.com.mdr.blogmultiplatform.util.Constants.FONT_FAMILY
import br.com.mdr.blogmultiplatform.util.Constants.LOGIN_INPUT_HEIGHT
import br.com.mdr.blogmultiplatform.util.Constants.LOGIN_INPUT_H_PADDING
import br.com.mdr.blogmultiplatform.util.Constants.LOGIN_INPUT_WIDTH
import br.com.mdr.blogmultiplatform.util.Constants.SMALL_BORDER_RADIUS
import br.com.mdr.blogmultiplatform.util.Constants.SMALL_MARGIN
import br.com.mdr.blogmultiplatform.util.Res
import com.varabyte.kobweb.compose.css.FontSize
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.VerticalAlign
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
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.modifiers.verticalAlign
import com.varabyte.kobweb.compose.ui.modifiers.visibility
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Input

@Page
@Composable
fun LoginScreen() {
    val errorText by remember { mutableStateOf("") }

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
                attrs = Modifier
                    .margin(bottom = SMALL_MARGIN.px)
                    .width(LOGIN_INPUT_WIDTH.px)
                    .height(LOGIN_INPUT_HEIGHT.px)
                    .fontFamily(FONT_FAMILY)
                    .padding(leftRight = LOGIN_INPUT_H_PADDING.px)
                    .backgroundColor(Colors.White)
                    .border(
                        width = 0.px,
                        style = LineStyle.None,
                        color = Colors.Transparent
                    )
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
                attrs = Modifier
                    .margin(bottom = SMALL_MARGIN.px)
                    .width(LOGIN_INPUT_WIDTH.px)
                    .height(LOGIN_INPUT_HEIGHT.px)
                    .fontFamily(FONT_FAMILY)
                    .padding(leftRight = LOGIN_INPUT_H_PADDING.px)
                    .backgroundColor(Colors.White)
                    .border(
                        width = 0.px,
                        style = LineStyle.None,
                        color = Colors.Transparent
                    )
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
                    .toAttrs()
            ) {
                SpanText(text = "Sign In")
            }
            SpanText(
                modifier = Modifier
                    .width(LOGIN_INPUT_WIDTH.px)
                    .color(Colors.Red)
                    .textAlign(TextAlign.Center)
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