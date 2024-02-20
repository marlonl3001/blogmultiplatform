package br.com.mdr.blogmultiplatform.pages.admin

import androidx.compose.runtime.Composable
import br.com.mdr.blogmultiplatform.components.AdminPageLayout
import br.com.mdr.blogmultiplatform.models.Joke
import br.com.mdr.blogmultiplatform.models.Theme
import br.com.mdr.blogmultiplatform.navigation.Screen
import br.com.mdr.blogmultiplatform.util.Constants.FONT_FAMILY
import br.com.mdr.blogmultiplatform.util.Constants.PAGE_WIDTH
import br.com.mdr.blogmultiplatform.util.Constants.SIDE_PANEL_WIDTH
import br.com.mdr.blogmultiplatform.util.Res
import br.com.mdr.blogmultiplatform.util.isUserLoggedIn
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.icons.fa.FaPlus
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.vh

@Page
@Composable
fun HomePage() {
    isUserLoggedIn {
        HomeScreen()
    }
}

@Composable
fun HomeScreen() {
    AdminPageLayout {
        HomeContent(
            joke = Joke(1, "Q:Você sabe porque o país está nesta merda?:Porque um merda de presidente foi " +
                    "eleito por uma merda de população.")
        )
        AddButton()
    }
}

@Composable
fun HomeContent(joke: Joke?) {
    val breakPoint = rememberBreakpoint()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(left = if (breakPoint > Breakpoint.MD) SIDE_PANEL_WIDTH.px else 0.px),
        contentAlignment = Alignment.Center
    ) {
        if (joke != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (joke.id != -1) {
                    Image(
                        modifier = Modifier
                            .size(150.px)
                            .margin(50.px),
                        src = Res.Image.laugh,
                        description = "Laugh image"
                    )
                    if (joke.joke.contains("Q:")) {
                        SpanText(
                            modifier = Modifier
                                .margin(bottom = 14.px)
                                .fillMaxWidth(60.percent)
                                .textAlign(TextAlign.Center)
                                .color(Theme.Secondary.rgb)
                                .fontFamily(FONT_FAMILY)
                                .fontSize(28.px)
                                .fontWeight(FontWeight.Bold),
                            text = joke.joke.split(":")[1]
                        )
                        SpanText(
                            modifier = Modifier
                                .fillMaxWidth(60.percent)
                                .textAlign(TextAlign.Center)
                                .color(Theme.HalfBlack.rgb)
                                .fontFamily(FONT_FAMILY)
                                .fontSize(20.px)
                                .fontWeight(FontWeight.Normal),
                            text = joke.joke.split(":").last()
                        )
                    } else {
                        SpanText(
                            modifier = Modifier
                                .margin(bottom = 14.px)
                                .fillMaxWidth(60.percent)
                                .textAlign(TextAlign.Center)
                                .color(Theme.Secondary.rgb)
                                .fontFamily(FONT_FAMILY)
                                .fontSize(28.px)
                                .fontWeight(FontWeight.Bold),
                            text = joke.joke.split(":")[1]
                        )
                    }
                }
            }
        } else {
            print("loading joke...")
        }
    }
}

@Composable
fun AddButton() {
    val breakpoint = rememberBreakpoint()
    val context = rememberPageContext()
    val buttonMargin = if (breakpoint > Breakpoint.MD) 40.px else 20.px

    Box(
        modifier = Modifier
            .height(100.vh)
            .fillMaxWidth()
            .maxWidth(PAGE_WIDTH.px)
            .position(Position.Fixed)
            .styleModifier {
                property("pointer-events", "none")
            },
        contentAlignment = Alignment.BottomEnd
    ) {
        Box(
            modifier = Modifier
                .margin(
                    bottom = buttonMargin,
                    right = buttonMargin
                )
                .backgroundColor(Theme.Primary.rgb)
                .size(if (breakpoint > Breakpoint.MD) 80.px else 50.px)
                .borderRadius(14.px)
                .cursor(Cursor.Pointer)
                .onClick {
                    context.router.navigateTo(Screen.Create.route)
                }
                .styleModifier {
                    property("pointer-events", "auto")
                },
            contentAlignment = Alignment.Center
        ) {
            FaPlus(
                modifier = Modifier.color(Colors.White),
                size = IconSize.LG
            )
        }
    }
}
