package com.morteza.shoppy.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.morteza.shoppy.model.slider.Sliders
import com.morteza.shoppy.viewmodel.HomeViewModel

@Composable
fun SlidersRow(vm: HomeViewModel) {
    when {
        vm.slider.value.isLoading -> {
            Loading(
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }

        vm.slider.value.error != null -> {
            ErrorBox(vm.slider.value.error ?: "Error", modifier = Modifier.height(200.dp))
        }


        vm.slider.value.data != null -> {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(vm.slider.value.data as List<Sliders>) { index, slider ->
                    AnimatedSlideIn(index * 100) {
                        SliderItem(slider)
                    }
                }
            }
        }


    }
}

@Composable
fun SliderItem(slider: Sliders) {
    Card(
        modifier = Modifier
            .width(300.dp)
            .height(200.dp)
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(20.dp),
                clip = true
            ),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(Modifier.fillMaxSize()) {
            AppImage(slider.image ?: "", slider.title ?: "")
            AppGradient(
                Modifier
                    .height(70.dp)
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    slider.title ?: "",
                    color = White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    slider.subTitle ?: "",
                    color = White,
                )

            }
        }

    }
}


