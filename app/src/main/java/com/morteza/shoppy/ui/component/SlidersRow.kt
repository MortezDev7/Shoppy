package com.morteza.shoppy.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.morteza.shoppy.viewmodel.HomeViewModel

@Composable
fun SlidersRow(vm: HomeViewModel) {
    DataUiStateHandler(
        state = vm.slider,
    ) { data ->
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            itemsIndexed(data) { index, slider ->
                AnimatedSlideIn(index * 100) {
                    AppCard(
                        modifier = Modifier
                            .width(300.dp)
                            .height(200.dp),
                        image = slider.image,
                        title = slider.title,
                        subTitle = slider.subTitle
                    )                }
            }
        }
    }

}

