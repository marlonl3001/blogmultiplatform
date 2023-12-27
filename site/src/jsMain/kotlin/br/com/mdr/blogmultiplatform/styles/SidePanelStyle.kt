package br.com.mdr.blogmultiplatform.styles

import br.com.mdr.blogmultiplatform.models.Theme
import br.com.mdr.blogmultiplatform.util.Ids
import com.varabyte.kobweb.compose.css.CSSTransition
import com.varabyte.kobweb.compose.css.TransitionProperty
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import org.jetbrains.compose.web.css.ms

val NavigationItemStyle by ComponentStyle {
    cssRule(" > #${Ids.svgParent} > #${Ids.vectorIcon}") {
        Modifier
            .transition(CSSTransition(property = TransitionProperty.All, duration = 300.ms))
            .styleModifier {
                property("stroke", Theme.White.hex)
            }
    }
    cssRule(":hover > #${Ids.svgParent} > #${Ids.vectorIcon}") {
        Modifier
            .styleModifier {
            property("stroke", Theme.Primary.hex)
        }
    }
    cssRule(" > #${Ids.navigationText}") {
        Modifier
            .transition(CSSTransition(property = TransitionProperty.All, duration = 300.ms))
            .color(Theme.White.rgb)
    }
    cssRule(":hover > #${Ids.navigationText}") {
        Modifier.color(Theme.Primary.rgb)
    }
}