package com.ao.recipeapp.ui.screens.auth.register_screen

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
fun RegisterScreen(navController: NavController) {
    val safeAreaTop = WindowInsets.safeContent.asPaddingValues().calculateTopPadding()
    val safeAreaBottom = WindowInsets.safeContent.asPaddingValues().calculateBottomPadding()
    val nameValue = remember { mutableStateOf("") }
    val surnameValue = remember { mutableStateOf("") }
    val usernameValue = remember { mutableStateOf("") }
    val emailValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }

    KeyboardCloser { size ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(top = safeAreaTop, bottom = safeAreaBottom, start = 20.dp, end = 20.dp)

        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(enabled = false, state = rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
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
                    AppText(
                        text = "Yemek\nTarifleri",
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                AppTextField(
                    value = nameValue,
                    onValueChange = { nameValue.value = it },
                    hintText = "İsim",
                    keyboardType = KeyboardType.Text
                )
                Spacer(modifier = Modifier.height(24.dp))
                AppTextField(
                    value = surnameValue,
                    onValueChange = { surnameValue.value = it },
                    hintText = "Soyisim",
                    keyboardType = KeyboardType.Text
                )
                Spacer(modifier = Modifier.height(24.dp))
                AppTextField(
                    value = usernameValue,
                    onValueChange = { usernameValue.value = it },
                    hintText = "Kulanıcı adı",
                    keyboardType = KeyboardType.Text
                )
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


                Spacer(
                    modifier = Modifier
                        .height(49.dp)
                )
                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .height(32.dp)
                        .align(alignment = Alignment.Start)
                ) {
                    AppText(
                        text = "Kullanıcı Sözleşmesi",
                        style = MaterialTheme.typography.bodyMedium.copy(color = AppColor.azul),
                    )
                }
                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .height(32.dp)
                        .align(alignment = Alignment.Start)
                ) {
                    AppText(
                        text = "Gizlilik Politikası",
                        style = MaterialTheme.typography.bodyMedium.copy(color = AppColor.azul),
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    AppButton(
                        text = "Geri",
                        onClick = { navController.popBackStack() },
                        outlined = true,
                        foregroundColor = AppColor.primary,
                        modifier = Modifier.weight(3f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    AppButton(
                        text = "Kayıt Ol",
                        onClick = { navController.navigate(Screens.Base.route) },
                        modifier = Modifier.weight(5f)
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    val navController = rememberNavController()
    RegisterScreen(navController = navController)
}