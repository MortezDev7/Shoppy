package com.morteza.shoppy.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.morteza.shoppy.ui.component.TopNavBar

@Composable
fun OnlineShopApp() {
    val navController = rememberNavController()
    val isFullScreen = checkForFullScreen(navController)

    Scaffold(
        topBar = { if(!isFullScreen) TopNavBar() },
        modifier = Modifier.fillMaxSize()) { innerPadding ->

        Box(
            Modifier
            .fillMaxSize()
            .padding(innerPadding)
        ) {
            NavHost(navController, startDestination = "home"){
                composable("home") { HomeScreen(navController) }

            }
        }
    }
}

@Composable
fun checkForFullScreen(navController: NavHostController) : Boolean {

    val fullScreenRoutes = listOf("login")
    val currentRoute =
        navController.currentBackStackEntryAsState().value?.destination?.route ?: "home"

    return fullScreenRoutes.any{
        currentRoute.startsWith(it)
    }
}
