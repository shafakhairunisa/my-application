package com.example.myapplication.presentation.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.presentation.main.MainActivity
import com.example.myapplication.presentation.setting.SettingViewModel
import com.example.myapplication.uikit.AppBarHeight
import com.example.myapplication.uikit.MyApplicationTheme
import com.example.myapplication.uikit.Spacing
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.ui.res.stringResource
import com.example.myapplication.R

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {

    private val viewModel: LoginViewModel by viewModels()
    private val settingViewModel: SettingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            val isDarkMode by settingViewModel.isDarkMode.collectAsState()

            MyApplicationTheme(darkTheme = isDarkMode) {
                LoginScreen(
                    onLoginClick = {
                        viewModel.login()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                )
            }
        }
    }
}

@Composable
fun LoginScreen(
    onLoginClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Spacing.l),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.welcome_message),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = Spacing.m)
            )

            Text(
                text = stringResource(R.string.login_subtitle),
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = Spacing.xl)
            )

            Button(
                onClick = onLoginClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(AppBarHeight)
            ) {
                Text(
                    text = stringResource(R.string.login),
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Preview(
    name = "Light Mode",
    showBackground = true
)
@Composable
fun LoginScreenLightPreview() {
    MyApplicationTheme(darkTheme = false) {
        LoginScreen(onLoginClick = {})
    }
}

@Preview(
    name = "Dark Mode",
    showBackground = true
)
@Composable
fun LoginScreenDarkPreview() {
    MyApplicationTheme(darkTheme = true) {
        LoginScreen(onLoginClick = {})
    }
}


