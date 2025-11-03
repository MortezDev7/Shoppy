package com.morteza.shoppy.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.morteza.shoppy.R
import com.morteza.shoppy.model.db.BasketEntity
import com.morteza.shoppy.ui.component.ProfileCard
import com.morteza.shoppy.ui.component.app.AppDialog
import com.morteza.shoppy.ui.component.app.AppImage
import com.morteza.shoppy.viewmodel.UserViewModel

@Composable
fun UserProfileScreen(
    navController: NavHostController,
    vm: UserViewModel = hiltViewModel(),
) {
    val currentUser by vm.currentUser.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(25.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(25.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppImage(
                R.drawable.avatar, "Person Image", modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
            )
            Column {
                Text(
                    currentUser?.firstName ?: "",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
                Text(
                    "@${currentUser?.username}",
                    color = Color.Gray,
                    fontSize = 16.sp
                )
            }
        }
        HorizontalDivider()

        Spacer(Modifier.height(20.dp))

        Column {
            ProfileCard(
                Icons.Filled.Star,
                "Invoices",
                onClick = {}
            )
            Spacer(Modifier.height(15.dp))
            ProfileCard(
                Icons.Filled.Lock,
                "Change Password",
                onClick = {}
            )
            Spacer(Modifier.height(15.dp))
            ProfileCard(
                Icons.Filled.Info,
                "Help",
                onClick = {}
            )
            Spacer(Modifier.height(15.dp))
            ProfileCard(
                Icons.AutoMirrored.Filled.Logout,
                "Logout",
                color = Color.Red,
                onClick = {
                    showDialog = true
                }
            )
        }
        AppDialog(
            showDialog,
            onDismiss = { showDialog = false },
            onCancel = { showDialog = false },
            onConfirm = {
                showDialog = false
                vm.logOut()
                navController.navigate("home") {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = false
                    }
                    launchSingleTop = true
                    restoreState = false
                }
            },
            title = "Logout",
            text = "Are You Sure You Want to Logout ?"
        )
    }
}