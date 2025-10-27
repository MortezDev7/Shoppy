package com.morteza.shoppy.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.morteza.shoppy.model.db.BasketEntity
import com.morteza.shoppy.ui.component.AppImage
import com.morteza.shoppy.ui.utils.formatPrice
import com.morteza.shoppy.viewmodel.BasketViewModel
import com.morteza.shoppy.viewmodel.SingleProductViewModel

@Composable
fun BasketScreen(
    navController: NavHostController,
    vm: BasketViewModel = hiltViewModel()
) {
    val basket by vm.basket.collectAsState()

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "Cart",
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp
        )

        Spacer(Modifier.height(10.dp))

        if (basket.isEmpty()) {
            Text("Basket Is Empty!")
        } else {
            LazyColumn {
                items(basket) { item ->
                    BasketItemRow(item,vm)
                    HorizontalDivider()
                }
            }
        }
    }
}

@Composable
fun BasketItemRow(
    item: BasketEntity,
    vm: BasketViewModel,
    singleVm: SingleProductViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .padding(vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Card(
                modifier = Modifier.size(60.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                AppImage(item.image ?: "", "Basket Image")
            }
            Column {
                Text(item.title ?: "", fontWeight = FontWeight.Bold)
                Text("${formatPrice(item.price ?: 0)} T", fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.weight(1f))
            Column {
                Text("Size:${item.size}", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                Spacer(Modifier.height(7.dp))
                Card(
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(
                        containerColor = Color("#${item.colorHex}".toColorInt())
                    ),
                    modifier = Modifier
                        .width(45.dp)
                        .height(25.dp)
                ) {}
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            var quantity by remember { mutableStateOf(1) }

            IconButton(onClick = {
                quantity++
            }) {
                Icon(Icons.Filled.KeyboardArrowUp, "Up")
            }

            Spacer(Modifier.width(10.dp))
            Text(quantity.toString())
            Spacer(Modifier.width(10.dp))

            IconButton(onClick = {
                if (quantity > 1) quantity--
            }) {
                Icon(Icons.Filled.KeyboardArrowDown, "Down")
            }

            Spacer(Modifier.weight(1f))

            IconButton(onClick = {
                vm.deleteItemFromBasket(item)
            }) {
                Icon(
                    Icons.Filled.Delete,
                    "Delete Icon",
                    modifier = Modifier.size(25.dp)
                )
            }
        }
    }
}
