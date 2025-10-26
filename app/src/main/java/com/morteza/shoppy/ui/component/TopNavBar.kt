package com.morteza.shoppy.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.morteza.shoppy.R
import com.morteza.shoppy.viewmodel.BasketViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavBar(
    vm: BasketViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val basket by vm.basket.collectAsState()

    TopAppBar(
        title = {
            AnimatedSlideIn(300) {
                Image(
                    painter = painterResource(R.drawable.logo1), contentDescription = "Online Shop",
                    modifier = Modifier.width(160.dp)
                )
            }

        },
        actions = {
            AnimatedSlideIn(600) {
                IconButton(
                    onClick = {
                        navController.navigate("basket")
                    },
                    modifier = Modifier.width(50.dp)
                ) {
                    BadgedBox(
                        badge = {
                            if (basket.isNotEmpty()) {
                                Badge {
                                    Text(basket.size.toString())
                                }
                            }
                        }

                    ) {
                        Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = "Basket")
                    }
                }
            }

            AnimatedSlideIn(900) {
                IconButton (
                    onClick = {

                    },
                    modifier = Modifier.width(50.dp)
                ) {
                    Icon(imageVector = Icons.Filled.Person, contentDescription = "Profile")
                }
            }

        }
    )

}