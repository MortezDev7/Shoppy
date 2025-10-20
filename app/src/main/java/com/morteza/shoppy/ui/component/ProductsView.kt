package com.morteza.shoppy.ui.component

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.morteza.shoppy.viewmodel.HomeViewModel

@Composable
fun ProductsView(vm: HomeViewModel) {
    Column{
        ProductFilterRow(vm)
        Spacer(Modifier.height(10.dp))
        ProductListView(vm)
    }
}

@Composable
fun ProductFilterRow(vm: HomeViewModel) {
    val filters = listOf("All", "New", "Popular")
    var selectedFilter by remember { mutableIntStateOf(0) }

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        itemsIndexed(filters) { index, item ->
            TextButton(
                onClick = {
                    selectedFilter = index
                    when (selectedFilter) {
                        0 -> vm.loadAllProducts()
                        1 -> vm.loadNewProducts()
                        2 -> vm.loadPopularProducts()
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor =
                        if (selectedFilter == index) Color.LightGray
                        else
                            Color.Transparent,
                    contentColor = if (isSystemInDarkTheme()) Color.White
                    else
                        Color.Black
                ),
                modifier = Modifier.width(90.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    item,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

            }
        }
    }
}

@Composable
fun ProductListView(vm: HomeViewModel) {
    DataUiStateHandler(
        state = vm.product,
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp),
    ) { data ->
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            itemsIndexed(data) { index, slider ->
                AnimatedSlideIn(
                    index * 100
                ) {
                    AppCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        image = slider.image,
                        title = slider.title,
                    )
                }
            }
        }
    }
}