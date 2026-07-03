package org.example.project


import org.example.project.ui.main.App
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.koin.core.context.startKoin
import org.example.project.di.appModule


fun main(){
    startKoin {
        modules(appModule)
    }

    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "IPpinger",
        ) {
            App()
        }
    }
}