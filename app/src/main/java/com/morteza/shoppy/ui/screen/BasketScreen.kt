package com.morteza.shoppy.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.morteza.shoppy.viewmodel.BasketViewModel

@Composable
fun BasketScreen(
    navController: NavHostController,
    vm: BasketViewModel = hiltViewModel()
) {
    val basket by vm.basket.collectAsState()

}