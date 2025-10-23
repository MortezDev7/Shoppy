package com.morteza.shoppy.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.morteza.shoppy.ui.component.AnimatedSlideIn
import com.morteza.shoppy.ui.component.AppCard
import com.morteza.shoppy.ui.component.DataUiStateHandler
import com.morteza.shoppy.viewmodel.ProductsViewModel

@Composable
fun ProductsScreen(
    catId: Long,
    title: String,
    navController: NavHostController,
    vm: ProductsViewModel = hiltViewModel()
) {

    val listState = rememberLazyListState()
    val shouldLoadMore by remember {
        derivedStateOf {
            val lastVisible = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            val totalItems = listState.layoutInfo.totalItemsCount
            lastVisible >= totalItems - 1
        }
    }

    LaunchedEffect(shouldLoadMore) {
        if (shouldLoadMore && !vm.product.isLoading){
            vm.loadProducts(catId)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        AnimatedSlideIn(delay = 600) {
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 26.sp)
        }
        Spacer(Modifier.height(20.dp))
            LazyColumn(
                state = listState,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(vm.product.data?: listOf()) { index, item ->
                    AnimatedSlideIn(index * 100) {
                        AppCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            image = item.image,
                            title = item.title,
                        )
                    }
                }
            }
        }
}