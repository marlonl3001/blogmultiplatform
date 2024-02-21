package br.com.mdr.blogmultiplatform.components

import androidx.compose.runtime.*
import br.com.mdr.blogmultiplatform.models.Theme
import br.com.mdr.blogmultiplatform.navigation.Screen
import br.com.mdr.blogmultiplatform.styles.NavigationItemStyle
import br.com.mdr.blogmultiplatform.util.Constants.COLLAPSED_PANEL_HEIGHT
import br.com.mdr.blogmultiplatform.util.Constants.FONT_FAMILY
import br.com.mdr.blogmultiplatform.util.Constants.SIDE_PANEL_WIDTH
import br.com.mdr.blogmultiplatform.util.Ids
import br.com.mdr.blogmultiplatform.util.Res
import br.com.mdr.blogmultiplatform.util.logout
import com.varabyte.kobweb.compose.css.CSSTransition
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.ScrollBehavior
import com.varabyte.kobweb.compose.dom.svg.Path
import com.varabyte.kobweb.compose.dom.svg.Svg
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.thenIf
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.icons.fa.FaBars
import com.varabyte.kobweb.silk.components.icons.fa.FaXmark
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.css.*

@Composable
fun SidePanel(onMenuClick: () -> Unit) {
    val breakpoint = rememberBreakpoint()

    if (breakpoint > Breakpoint.MD) {
        SidePanelInternal()
    } else {
        CollapsedSidePanel(onMenuClick)
    }
}
@Composable
fun SidePanelInternal() {
    Column(
        Modifier
            .height(100.vh)//VH is Viewport Height, which means that It's the height of the browser window
            .width(SIDE_PANEL_WIDTH.px)
            .padding(leftRight = 40.px, topBottom = 50.px)
            .backgroundColor(Theme.Secondary.rgb)
            .position(Position.Fixed)
            .zIndex(9)//zIndex is the order of the element on the screen
    ) {
        Image(
            modifier = Modifier
                .margin(bottom = 60.px),
            src = Res.Image.logo,
            description = "Image logo"
        )
        NavigationItems()
    }
}

@Composable
fun OverflowSidePanel(onCloseMenu: () -> Unit) {
    val scope = rememberCoroutineScope()
    val breakpoint = rememberBreakpoint()

    var translateX by remember { mutableStateOf((-100).percent) }
    var opacity by remember { mutableStateOf(0.percent) }

    LaunchedEffect(breakpoint) {
        translateX = 0.percent
        opacity = 100.percent
        if (breakpoint > Breakpoint.MD) {
            scope.launch {
                translateX = (-100).percent
                opacity = 0.percent
                delay(500)
                onCloseMenu()
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.vh)
            .position(Position.Fixed)
            .zIndex(9)
            .backgroundColor(Theme.HalfBlack.rgb)
            .opacity(opacity)
            .transition(CSSTransition(property = "opacity", duration = 300.ms))
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(if (breakpoint < Breakpoint.MD) 50.percent else 25.percent)
                .padding(leftRight = 24.px, topBottom = 40.px)
                .backgroundColor(Theme.Secondary.rgb)
                .overflow(Overflow.Auto)
                .scrollBehavior(ScrollBehavior.Smooth)
                .translateX(translateX)
                .transition(CSSTransition(property = "translate", duration = 300.ms))
        ) {
            Row(
                modifier = Modifier.margin(bottom = 60.px),
                verticalAlignment = Alignment.CenterVertically
            ) {
                FaXmark(
                    modifier = Modifier
                        .margin(right = 20.px)
                        .color(Colors.White)
                        .cursor(Cursor.Pointer)
                        .onClick {
                            scope.launch {
                                translateX = (-100).percent
                                opacity = 0.percent
                                delay(500)
                                onCloseMenu()
                            }
                        },
                    size = IconSize.LG
                )
                Image(
                    modifier = Modifier.width(80.px),
                    src = Res.Image.logo,
                    description = "Image logo"
                )
            }
            NavigationItems()
        }
    }
}

@Composable
private fun NavigationItems() {
    val context = rememberPageContext()

    SpanText(
        modifier = Modifier
            .margin(bottom = 30.px)
            .fontFamily(FONT_FAMILY)
            .fontSize(14.px)
            .color(Theme.HalfWhite.rgb),
        text = "Dashboard"
    )

    NavigationItem(
        modifier = Modifier.margin(bottom = 24.px),
        selected = context.route.path == Screen.Home.route,
        title = "Home",
        icon = Res.PathIcon.home,
        onClick = { context.router.navigateTo(Screen.Home.route) }
    )

    NavigationItem(
        modifier = Modifier.margin(bottom = 24.px),
        selected = context.route.path == Screen.Create.route,
        title = "Create Post",
        icon = Res.PathIcon.create,
        onClick = { context.router.navigateTo(Screen.Create.route) }
    )

    NavigationItem(
        modifier = Modifier.margin(bottom = 24.px),
        selected = context.route.path == Screen.MyPosts.route,
        title = "My Posts",
        icon = Res.PathIcon.posts,
        onClick = { context.router.navigateTo(Screen.MyPosts.route) }
    )

    NavigationItem(
        title = "Logout",
        icon = Res.PathIcon.logout,
        onClick = {
            logout()
        }
    )
}

@Composable
private fun NavigationItem(
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    title: String,
    icon: String,
    onClick: () -> Unit
) {
    Row(
        modifier = NavigationItemStyle
            .toModifier()
            .then(modifier)
            .cursor(Cursor.Pointer)
            .onClick { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        VectorIcon(
            modifier = Modifier
                .margin(right = 20.px),
            selected = selected,
            pathData = icon
        )
        SpanText(
            modifier = Modifier
                .id(Ids.navigationText)
                .fontFamily(FONT_FAMILY)
                .fontSize(16.px)
                .thenIf(
                    condition = selected,
                    other = Modifier.color(Theme.Primary.rgb)
                ),
            text = title
        )
    }
}

@Composable
private fun VectorIcon(
    modifier: Modifier = Modifier,
    selected: Boolean,
    pathData: String
) {
    Svg(
        attrs = modifier
            .id(Ids.svgParent)
            .width(24.px)
            .height(24.px)
            .toAttrs {
                attr("viewBox", "0 0 24 24")
                attr("fill", "none")
            }
    ) {
        Path {
            if (selected) {
                attr(attr = "style", value = "stroke: ${Theme.Primary.hex}")
            }
            attr(attr = "id", value = Ids.vectorIcon)
            attr(attr = "d", value = pathData)
            attr(attr = "stroke-width", value = "2")
            attr(attr = "stroke-linecap", value = "round")
            attr(attr = "stroke-linejoin", value = "round")
        }
    }
}

@Composable
private fun CollapsedSidePanel(onMenuClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(COLLAPSED_PANEL_HEIGHT.px)
            .padding(leftRight = 24.px)
            .backgroundColor(Theme.Secondary.rgb),
        verticalAlignment = Alignment.CenterVertically
    ) {
        FaBars(
            modifier = Modifier
                .margin(right = 24.px)
                .color(Colors.White)
                .cursor(Cursor.Pointer)
                .onClick { onMenuClick() }
        )
        Image(
            modifier = Modifier.width(80.px),
            src = Res.Image.logo,
            description = "Image logo"
        )
    }
}
