package com.morteza.shoppy.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.morteza.shoppy.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavBar() {

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
                TextButton(
                    onClick = {

                    },
                    modifier = Modifier.width(50.dp)
                ) {
                    Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = "Basket")
                }
            }

            AnimatedSlideIn(900) {
                TextButton(
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