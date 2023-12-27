package br.com.mdr.blogmultiplatform.components

import androidx.compose.runtime.Composable
import br.com.mdr.blogmultiplatform.models.Theme
import br.com.mdr.blogmultiplatform.navigation.Screen
import br.com.mdr.blogmultiplatform.styles.NavigationItemStyle
import br.com.mdr.blogmultiplatform.util.Constants.FONT_FAMILY
import br.com.mdr.blogmultiplatform.util.Constants.SIDE_PANEL_WIDTH
import br.com.mdr.blogmultiplatform.util.Ids
import br.com.mdr.blogmultiplatform.util.Res
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.dom.svg.Path
import com.varabyte.kobweb.compose.dom.svg.Svg
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.modifiers.zIndex
import com.varabyte.kobweb.compose.ui.thenIf
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.vh

@Composable
fun SidePanel() {
    
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
fun NavigationItems() {
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
        onClick = {}
    )
}

@Composable
fun NavigationItem(
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