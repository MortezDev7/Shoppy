package com.morteza.shoppy.ui.screen

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.morteza.shoppy.model.api.customers.UserDto
import com.morteza.shoppy.ui.component.app.AppTextField
import com.morteza.shoppy.viewmodel.BasketViewModel
import com.morteza.shoppy.viewmodel.LoginViewModel
import com.morteza.shoppy.viewmodel.UserPaymentViewModel
import com.morteza.shoppy.viewmodel.UserViewModel

@Composable
fun UserPaymentScreen(navController: NavHostController) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
            }
            Spacer(Modifier.width(5.dp))
            Text("Complete Your Information", textAlign = TextAlign.Center, fontSize = 22.sp)
        }
        TextFieldColumn(navController = navController)
    }
}

@Composable
fun TextFieldColumn(
    basketVm: BasketViewModel = hiltViewModel(),
    payVm: UserPaymentViewModel = hiltViewModel(),
    navController: NavHostController,
    loginVm: LoginViewModel = hiltViewModel(),
    userVm: UserViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val basket by basketVm.basket.collectAsState()
    val currentUser by userVm.currentUser.collectAsState()

    var firstName by remember { mutableStateOf(TextFieldValue("")) }
    var firstNameError by remember { mutableStateOf(false) }

    var lastName by remember { mutableStateOf(TextFieldValue("")) }
    var lastNameError by remember { mutableStateOf(false) }

    var phoneNumber by remember { mutableStateOf(TextFieldValue("")) }
    var phoneNumberError by remember { mutableStateOf(false) }

    var userName by remember { mutableStateOf(TextFieldValue("")) }
    var userNameError by remember { mutableStateOf(false) }

    var password by remember { mutableStateOf(TextFieldValue("")) }
    var passwordError by remember { mutableStateOf(false) }

    var postalCode by remember { mutableStateOf(TextFieldValue("")) }
    var postalCodeError by remember { mutableStateOf(false) }

    var address by remember { mutableStateOf(TextFieldValue("")) }
    var addressError by remember { mutableStateOf(false) }

    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(currentUser) {
        currentUser?.let { user ->
            firstName = TextFieldValue(user.firstName ?: "")
            lastName = TextFieldValue(user.lastName ?: "")
            phoneNumber = TextFieldValue(user.phone ?: "")
            userName = TextFieldValue(user.username ?: "")
            postalCode = TextFieldValue(user.postalCode ?: "")
            address = TextFieldValue(user.address ?: "")
        }
    }

    LazyColumn {
        item {
            Column(Modifier.padding(20.dp)) {
                AppTextField(
                    "First Name",
                    firstName,
                    onValueChange = {
                        firstName = it
                        firstNameError = false
                    },
                    firstNameError
                )
                Spacer(Modifier.height(10.dp))
                AppTextField(
                    label = "Last Name",
                    textValue = lastName,
                    onValueChange = {
                        lastName = it
                        lastNameError = false
                    },
                    isError = lastNameError
                )
                Spacer(Modifier.height(10.dp))

                AppTextField(
                    label = "Phone Number",
                    textValue = phoneNumber,
                    onValueChange = {
                        phoneNumber = it
                        phoneNumberError = false
                    },
                    isError = phoneNumberError,
                    keyboardType = KeyboardType.Number
                )
                Spacer(Modifier.height(10.dp))

                AppTextField(
                    label = "Username",
                    textValue = userName,
                    onValueChange = {
                        userName = it
                        userNameError = false
                    },
                    isError = userNameError,
                    enabled = currentUser == null
                )

                Spacer(Modifier.height(10.dp))

                if (currentUser == null)
                    AppTextField(
                        label = "Password",
                        textValue = password,
                        onValueChange = {
                            password = it
                            passwordError = false
                        },
                        isError = passwordError,
                        keyboardType = KeyboardType.Password,
                        visualTransformation = PasswordVisualTransformation()
                    )

                Spacer(Modifier.height(10.dp))

                AppTextField(
                    label = "PostalCode",
                    textValue = postalCode,
                    onValueChange = {
                        postalCode = it
                        postalCodeError = false
                    },
                    isError = postalCodeError,
                )

                Spacer(Modifier.height(10.dp))

                AppTextField(
                    label = "Address",
                    textValue = address,
                    onValueChange = {
                        address = it
                        addressError = false
                    },
                    isError = addressError,
                    singleLine = false,
                    imeAction = ImeAction.Done
                )
            }
        }
        item {
            Box(Modifier.padding(15.dp)) {
                Button(
                    onClick = {
                        if (isLoading) return@Button

                        firstNameError = firstName.text.isEmpty()
                        lastNameError = lastName.text.isEmpty()
                        phoneNumberError = phoneNumber.text.isEmpty()
                        userNameError = userName.text.isEmpty()
                        passwordError = currentUser == null && password.text.isEmpty()
                        postalCodeError = postalCode.text.isEmpty()
                        addressError = address.text.isEmpty()

                        if (
                            firstNameError || lastNameError || phoneNumberError || passwordError
                            || postalCodeError || addressError || userNameError
                        ) {
                            return@Button
                        } else {
                            val userInfo = UserDto(
                                id = if (currentUser == null) null else currentUser?.userId,
                                customerId = if (currentUser == null) null else currentUser?.customerId,
                                username = userName.text,
                                password = password.text,
                                address = address.text,
                                firstName = firstName.text,
                                lastName = lastName.text,
                                postalCode = postalCode.text,
                                phone = phoneNumber.text
                            )

                            payVm.goToPayment(
                                userInfo,
                                basket,
                                onLoading = {
                                    isLoading = true
                                },
                                onError = {
                                    isLoading = false
                                    Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                                },
                                onSuccess = {
                                    isLoading = false
                                    val intent = Intent(Intent.ACTION_VIEW, it)
                                    navController.navigate("home") {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = false
                                        }
                                        launchSingleTop = true
                                        restoreState = false
                                    }
                                    context.startActivity(intent)
                                },
                                loginVm
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (isLoading) {
                        CircularProgressIndicator()
                    } else {
                        Text("\$Pay")
                    }
                }
            }
        }
    }
}

