package com.example.myapplication.presentation.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.data.local.PreferencesManager
import com.example.myapplication.data.repository.AuthRepositoryImpl
import com.example.myapplication.domain.usecase.CheckLoginStatusUseCase
import com.example.myapplication.presentation.login.LoginActivity
import com.example.myapplication.presentation.main.MainActivity
import com.example.myapplication.uikit.MyApplicationTheme

class SplashScreenActivity : ComponentActivity() {

    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize ViewModel with dependencies
        val preferencesManager = PreferencesManager(this)
        val authRepository = AuthRepositoryImpl(preferencesManager)
        val checkLoginStatusUseCase = CheckLoginStatusUseCase(authRepository)
        val factory = SplashViewModelFactory(checkLoginStatusUseCase)
        viewModel = ViewModelProvider(this, factory)[SplashViewModel::class.java]

        setContent {
            MyApplicationTheme {
                SplashScreen(
                    onNavigate = { isLoggedIn ->
                        val intent = if (isLoggedIn) {
                            Intent(this, MainActivity::class.java)
                        } else {
                            Intent(this, LoginActivity::class.java)
                        }
                        startActivity(intent)
                        finish()
                    },
                    viewModel = viewModel
                )
            }
        }
    }
}

@Composable
fun SplashScreen(
    onNavigate: (Boolean) -> Unit,
    viewModel: SplashViewModel
) {
    LaunchedEffect(Unit) {
        val isLoggedIn = viewModel.isLoggedIn()
        onNavigate(isLoggedIn)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    MyApplicationTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

