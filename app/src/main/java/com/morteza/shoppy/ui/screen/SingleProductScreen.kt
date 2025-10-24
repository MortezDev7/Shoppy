package com.morteza.shoppy.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.morteza.shoppy.ui.component.AnimatedSlideIn
import com.morteza.shoppy.ui.component.AppGradient
import com.morteza.shoppy.ui.component.AppImage
import com.morteza.shoppy.ui.utils.formatPrice
import com.morteza.shoppy.viewmodel.SingleProductViewModel

@Composable
fun SingleProductScreen(
    id: Long,
    navController: NavHostController,
    innerPadding: PaddingValues,
    vm: SingleProductViewModel = hiltViewModel()
) {
    LaunchedEffect(id) {
        vm.loadProduct(id)
    }
    Box(Modifier.fillMaxSize()) {
        AppImage(vm.product?.image ?: "", description = vm.product?.title ?: "")
        AppGradient(
            modifier = Modifier
                .height(600.dp)
                .align(Alignment.BottomCenter)
        )
        Box(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            IconButton(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier.align(Alignment.TopStart)
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "back")
            }
            Column(
                modifier = Modifier
                    .align(
                        Alignment.BottomStart
                    )
                    .padding(20.dp)
            ) {
                AnimatedSlideIn {
                    Text(
                        vm.product?.title ?: "",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp
                    )
                }
                Spacer(Modifier.height(10.dp))
                AnimatedSlideIn(200) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            formatPrice(vm.product?.price ?: 0),
                            color = Color.White,
                            fontSize = 26.sp
                        )
                        Spacer(Modifier.width(6.dp))
                        Text(
                            text = "Toman",
                            color = Color.LightGray,
                            fontSize = 16.sp
                        )
                    }

                }

                Spacer(Modifier.height(20.dp))

                ProductSizesRow(vm)

                Spacer(Modifier.height(20.dp))

                ProductColorsRow(vm)

                Spacer(Modifier.height(20.dp))

                AnimatedSlideIn(1800, duration = 3000) {
                    Text(
                        vm.product?.description ?: "",
                        color = Color.LightGray,
                        fontSize = 16.sp
                    )
                }
                Spacer(Modifier.height(25.dp))

                AnimatedSlideIn(3000) {
                    Button(
                        onClick = {

                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        )
                    ) {
                        Text("Buy Now", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    }
                }
                Spacer(Modifier.height(20.dp))

            }

        }
    }
}

@Composable
fun ProductSizesRow(vm: SingleProductViewModel) {
    AnimatedSlideIn(400) {
        Text(
            "Sizes",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp
        )
    }

    Spacer(Modifier.height(10.dp))

    LazyRow {
        itemsIndexed(vm.product?.sizes ?: listOf()) { index, item ->
            AnimatedSlideIn(600 + (index * 200)) {
                TextButton(
                    onClick = {
                        vm.selectedSize = item
                    }, colors = ButtonDefaults.buttonColors(
                        containerColor = if (vm.selectedSize == item) Color.White else Color.Transparent,
                        contentColor = if (vm.selectedSize == item) Color.Black else Color.White
                    ),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Text(
                        item?.title ?: "",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Spacer(Modifier.width(5.dp))
        }
    }
}

@Composable
fun ProductColorsRow(vm: SingleProductViewModel) {
    Column {
        AnimatedSlideIn(1000) {
            Text(
                "Colors",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp
            )
        }
        Spacer(Modifier.height(10.dp))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(7.dp)) {
            itemsIndexed(vm.product?.colors ?: listOf()) { index, item ->
                AnimatedSlideIn(1200 + (index * 200)) {
                    TextButton(
                        onClick = {
                            vm.selectedColor = item
                        },
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color("#${item?.hexValue}".toColorInt())
                        ),
                        border = BorderStroke(
                            2.dp,
                            color = if (vm.selectedColor == item) Color.Red else Color.White
                        ),
                        modifier = Modifier.size(40.dp)
                    ) {
                        if (vm.selectedColor == item) {
                            Icon(Icons.Filled.Check, contentDescription = "Selected Item Check")
                        }
                    }
                }
            }
        }
    }
}

