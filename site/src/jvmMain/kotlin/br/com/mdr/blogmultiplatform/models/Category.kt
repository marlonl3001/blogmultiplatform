package br.com.mdr.blogmultiplatform.models

import kotlinx.serialization.Serializable

@Serializable
actual enum class Category(val color: String) {
    Technology(color = Theme.Green.hex),
    Programming(color = Theme.Yellow.hex),
    Design(Theme.Purple.hex)
}