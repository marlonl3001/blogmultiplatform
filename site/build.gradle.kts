import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import kotlinx.html.link
import kotlinx.html.script

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kobweb.application)
    alias(libs.plugins.kotlin.serialization)
}

group = "br.com.mdr.blogmultiplatform"
version = "1.0-SNAPSHOT"

kobweb {
    app {
        index {
            description.set("Powered by Kobweb")

            head.add {
                script {
                    src = "https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                }
                link {
                    rel = "stylesheet"
                    href = "https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
                }
            }
        }
    }
}

kotlin {
    configAsKobwebApplication("blogmultiplatform", includeServer = true)

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(libs.kotlin.serialization)
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(compose.html.core)
                implementation(libs.kobweb.core)
                implementation(libs.kobweb.silk)
                implementation(libs.silk.icons.fa)
                implementation(libs.kotlin.serialization)
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(libs.kobweb.api)
                //MongoDB
                implementation(libs.mongo.db.kotlin)
                implementation(libs.kotlin.serialization)
            }
        }
    }
}
