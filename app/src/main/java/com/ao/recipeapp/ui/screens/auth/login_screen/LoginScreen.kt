package com.ao.recipeapp.ui.screens.auth.login_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ao.recipeapp.R
import com.ao.recipeapp.ui.component.AppButton
import com.ao.recipeapp.ui.component.AppText
import com.ao.recipeapp.ui.component.AppTextField
import com.ao.recipeapp.ui.component.KeyboardCloser
import com.ao.recipeapp.ui.navigation.Screens
import com.ao.recipeapp.ui.theme.AppColor
import com.ao.recipeapp.ui.theme.latoFamily

@Composable
fun LoginScreen(navController: NavController) {
    val safeAreaTop = WindowInsets.safeContent.asPaddingValues().calculateTopPadding()
    val safeAreaBottom = WindowInsets.safeContent.asPaddingValues().calculateBottomPadding()
    val emailValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }

    KeyboardCloser { size ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .verticalScroll(enabled = false, state = rememberScrollState())
                .padding(top = safeAreaTop, bottom = safeAreaBottom, start = 20.dp, end = 20.dp)

        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = (size.height * 0.06).dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_logo_svg),
                        contentDescription = null,
                        modifier = Modifier.size((size.width * 0.2).dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    AppText(text = "Yemek\nTarifleri", style = MaterialTheme.typography.headlineMedium)
                }

                Spacer(modifier = Modifier.height(24.dp))
                AppTextField(
                    value = emailValue,
                    onValueChange = { emailValue.value = it },
                    hintText = "Email veya kullanıcı adı",
                    keyboardType = KeyboardType.Email
                )
                Spacer(modifier = Modifier.height(24.dp))
                AppTextField(
                    value = passwordValue,
                    onValueChange = { passwordValue.value = it },
                    hintText = "Şifre",
                    keyboardType = KeyboardType.Password,
                    isPassword = true
                )
                Spacer(modifier = Modifier.height(110.dp))
                TextButton(onClick = { /*TODO*/ }) {
                    AppText(
                        text = "Şifreni mi unuttun?",
                        style = MaterialTheme.typography.bodyMedium.copy(color = AppColor.primary),
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                AppButton(
                    text = "Giriş Yap",
                    onClick = {
                        navController.navigate(Screens.Base.route) {
                            popUpTo(Screens.Auth.Login.route) { inclusive = true }
                            launchSingleTop = true
                        }
                    },
                )
                AppText(
                    text = "veya",
                    style = MaterialTheme.typography.bodyMedium.copy(color = AppColor.neutral40),
                    modifier = Modifier.padding(vertical = 24.dp)
                )
                AppButton(
                    text = "Kayıt Ol",
                    onClick = { navController.navigate(Screens.Auth.Register.route) },
                    outlined = true,
                    foregroundColor = AppColor.primary,
                )
            }
        }
    }
}


@Preview
@Composable
fun PreviewLoginScreen() {
    val navController = rememberNavController()
    LoginScreen(navController = navController)
}