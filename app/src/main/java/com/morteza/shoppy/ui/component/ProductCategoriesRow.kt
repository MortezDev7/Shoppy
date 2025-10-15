package com.morteza.shoppy.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.morteza.shoppy.viewmodel.HomeViewModel

@Composable
fun ProductCategoriesRow(vm: HomeViewModel) {

    DataUiStateHandler(
        state = vm.productCategory, modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) { data ->
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            itemsIndexed(data) { index, item ->
                AnimatedSlideIn(index * 100) {
                    AppCard(
                        modifier = Modifier
                            .width(160.dp)
                            .height(200.dp),
                        image = item.image,
                        title = item.title,
                    )
                }
            }
        }
    }

}

