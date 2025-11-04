package com.morteza.shoppy.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
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
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.morteza.shoppy.ui.component.ProfileCard
import com.morteza.shoppy.ui.component.app.AppDialog
import com.morteza.shoppy.ui.component.graphic.AnimatedSlideIn
import com.morteza.shoppy.ui.theme.AppGreen
import com.morteza.shoppy.viewmodel.InvoiceViewModel
import com.morteza.shoppy.viewmodel.UserViewModel

@Composable
fun InvoicesScreen(
    navController: NavHostController,
    vm: InvoiceViewModel = hiltViewModel(),
    userVm: UserViewModel = hiltViewModel()
) {
    val currentUser by userVm.currentUser.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    val listState = rememberLazyListState()

    val shouldLoadMore by remember {
        derivedStateOf {
            val lastVisible = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            val totalItems = listState.layoutInfo.totalItemsCount
            lastVisible >= totalItems - 2
        }
    }

    if (currentUser != null && vm.invoices.data.isNullOrEmpty()) {
        LaunchedEffect(shouldLoadMore) {
            vm.loadInvoices(currentUser?.userId!!, currentUser?.token!!)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AnimatedSlideIn(delay = 200) {
                Text(
                    text = "Invoices",
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp
                )
            }
            AnimatedSlideIn(delay = 400) {
                IconButton(onClick = { showDialog = true }) {
                    Icon(Icons.Filled.Delete, contentDescription = "Delete Invoices")
                }
            }
        }
        Spacer(Modifier.height(20.dp))
        LazyColumn(
            state = listState,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(vm.invoices.data ?: listOf()) { index, item ->
                AnimatedSlideIn(index * 100) {
                    ProfileCard(
                        text = "${item.addDate ?: "-"} (${item.status ?: "-"})",
                        icon = if (item.status == "NotPayed") Icons.Filled.Close else Icons.Filled.Check,
                        color = if (item.status == "NotPayed") Color.Red else AppGreen
                    ) {
                        navController.navigate("invoice/${item.id}")
                    }
                }
            }
        }
    }
    AppDialog(
        showDialog,
        onDismiss = { showDialog = false },
        onCancel = { showDialog = false },
        onConfirm = {
            showDialog = false
        },
        title = "Delete Invoices",
        text = "Are You Sure You Want to Delete Invoices ?"
    )
}