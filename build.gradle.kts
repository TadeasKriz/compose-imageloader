plugins {
    id("com.android.application").apply(false)
    id("com.android.library").apply(false)
    kotlin("android").apply(false)
    // id("com.diffplug.spotless").version(Versions.spotless)
}

allprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = freeCompilerArgs + listOf(
                "-opt-in=kotlin.RequiresOptIn",
                "-opt-in=androidx.compose.ui.ExperimentalComposeUiApi",
                "-Xcontext-receivers",
                "-Xskip-prerelease-check",
            )
        }
    }

    // moko-resources will error with spotless
    // task error: Cannot change attributes of dependency configuration ':app:common:iosArm64ApiElements' after it has been resolved

    // apply(plugin = "com.diffplug.spotless")
    // spotless {
    //     kotlin {
    //         target("**/*.kt")
    //         targetExclude("$buildDir/**/*.kt", "bin/**/*.kt", "buildSrc/**/*.kt")
    //         ktlint(Versions.ktlint)
    //         // licenseHeaderFile(rootProject.file("spotless/license"))
    //     }
    //     kotlinGradle {
    //         target("*.gradle.kts")
    //         ktlint(Versions.ktlint)
    //     }
    //     java {
    //         target("**/*.java")
    //         targetExclude("$buildDir/**/*.java", "bin/**/*.java")
    //         // licenseHeaderFile(rootProject.file("spotless/license"))
    //     }
    // }
}
