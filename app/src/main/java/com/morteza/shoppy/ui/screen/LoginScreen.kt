package com.morteza.shoppy.ui.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.morteza.shoppy.ui.component.app.AppTextField
import com.morteza.shoppy.ui.theme.AppDarkGray
import com.morteza.shoppy.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    navController: NavHostController,
    vm: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    var userName by remember { mutableStateOf(TextFieldValue("")) }
    var userNameError by remember { mutableStateOf(false) }

    var password by remember { mutableStateOf(TextFieldValue("")) }
    var passwordError by remember { mutableStateOf(false) }

    var isLoading by remember { mutableStateOf(false) }

    Box(
        Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        AppDarkGray,
                        Color.Black
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            Modifier
                .padding(24.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text(
                    "Welcome Back",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                AppTextField(
                    label = "Username",
                    textValue = userName,
                    onValueChange = {
                        userName = it
                        userNameError = false
                    },
                    isError = userNameError,
                    shape = RoundedCornerShape(15.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        cursorColor = Color.Black,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.Gray

                    )
                )
                AppTextField(
                    label = "Password",
                    textValue = password,
                    onValueChange = {
                        password = it
                        passwordError = false
                    },
                    isError = passwordError,
                    keyboardType = KeyboardType.Password,
                    visualTransformation = PasswordVisualTransformation(),
                    shape = RoundedCornerShape(15.dp),
                    imeAction = ImeAction.Done,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        cursorColor = Color.Black,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.Gray
                    )
                )

                Button(
                    onClick = {
                        userNameError = userName.text.isEmpty()
                        passwordError = password.text.isEmpty()
                        if (userNameError || passwordError) return@Button

                        vm.login(
                            username = userName.text,
                            password = password.text,
                            onLoading = {
                                isLoading = true
                            },
                            onError = {
                                isLoading = false
                                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                            },
                            onSuccess = {
                                isLoading = false
                                Toast.makeText(context, "Welcome Back Dear ${it.firstName}", Toast.LENGTH_SHORT).show()
                                navController.navigate("userProfile"){
                                    popUpTo(navController.graph.findStartDestination().id){
                                        saveState = false
                                    }
                                    launchSingleTop =true
                                    restoreState = false
                                }
                            }
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    if (isLoading)
                        CircularProgressIndicator()
                    else
                    Text("Login")
                }
            }
        }
    }
}