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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.morteza.shoppy.ui.component.graphic.AnimatedSlideIn
import com.morteza.shoppy.ui.component.app.AppDialog
import com.morteza.shoppy.ui.component.app.AppImage
import com.morteza.shoppy.ui.theme.AppGreen
import com.morteza.shoppy.ui.utils.formatPrice
import com.morteza.shoppy.viewmodel.BasketViewModel

@Composable
fun BasketScreen(
    navController: NavHostController,
    vm: BasketViewModel = hiltViewModel()
) {
    val basket by vm.basket.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var itemToDelete by remember { mutableStateOf<BasketEntity?>(null) }
    val totalPrice = basket.sumOf {
        (it.price ?: 0) * it.quantity
    }

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
            LazyColumn(Modifier.weight(1f)) {
                itemsIndexed(basket) { index, item ->
                    AnimatedSlideIn(index * 200) {
                        Column {
                            BasketItemRow(
                                item = item,
                                onIncrease = { vm.increaseQuantity(item) },
                                onDecrease = { vm.decreaseQuantity(item) },
                                onRemove = {
                                    showDialog = true
                                    itemToDelete = item
                                }
                            )
                            HorizontalDivider()
                        }
                    }
                }
            }
            Spacer(Modifier.height(10.dp))

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Total", fontWeight = FontWeight.Bold)
                Text("${formatPrice(totalPrice)} T", fontSize = 16.sp)
            }
            Spacer(Modifier.height(25.dp))

            Row(Modifier.fillMaxWidth()) {
                TextButton(
                    onClick = {
                        navController.navigate("home")
                    },
                    modifier = Modifier.weight(0.5f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray,
                        contentColor = Color.Black
                    )
                ) {
                    Text("Continue Shopping")
                }
                Spacer(Modifier.width(10.dp))
                TextButton(
                    onClick = {
                        navController.navigate("userPayment")
                    },
                    modifier = Modifier.weight(0.5f),
                    colors = ButtonDefaults.buttonColors(containerColor = AppGreen)
                ) {
                    Text("Proceed to Payment")
                }
            }
        }
    }
    AppDialog(
        showDialog,
        onCancel = { showDialog = false },
        onConfirm = {
            showDialog = false
            vm.deleteItemFromBasket(itemToDelete!!)
        },
        onDismiss = { showDialog = false },
        title = "Delete Item",
        text = "Do You Want To Delete Item"
    )
}

@Composable
fun BasketItemRow(
    item: BasketEntity,
    onIncrease: (BasketEntity) -> Unit,
    onDecrease: (BasketEntity) -> Unit,
    onRemove: (BasketEntity) -> Unit
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
                Text(
                    "${formatPrice((item.price ?: 0) * item.quantity)} T",
                    fontWeight = FontWeight.Bold
                )
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

            IconButton(onClick = {
                onIncrease(item)
            }) {
                Icon(Icons.Filled.KeyboardArrowUp, "Up")
            }

            Spacer(Modifier.width(10.dp))
            Text("${item.quantity}")
            Spacer(Modifier.width(10.dp))

            IconButton(onClick = {
                onDecrease(item)
            }) {
                Icon(Icons.Filled.KeyboardArrowDown, "Down")
            }

            Spacer(Modifier.weight(1f))

            IconButton(onClick = {
                onRemove(item)
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
