package com.morteza.shoppy.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.morteza.shoppy.ui.component.ProductCategoriesRow
import com.morteza.shoppy.ui.component.ProductsView
import com.morteza.shoppy.ui.component.SlidersRow
import com.morteza.shoppy.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    vm : HomeViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .verticalScroll(rememberScrollState())

    ) {

        SlidersRow(vm)
        Spacer(Modifier.height(10.dp))
        ProductCategoriesRow(vm,navController)
        Spacer(Modifier.height(10.dp))
        ProductsView(vm)
    }
}