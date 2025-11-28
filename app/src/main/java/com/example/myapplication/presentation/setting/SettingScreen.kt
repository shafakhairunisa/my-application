package com.example.myapplication.presentation.setting

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.uikit.MyApplicationTheme

@Composable
fun SettingScreen(
    viewModel: SettingViewModel
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Toolbar
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                color = Color(0xFF212121)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Settings",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
            }

            // Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        viewModel.logout()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Text(
                        text = "Logout",
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingScreenPreview() {
    MyApplicationTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // Toolbar
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    color = Color(0xFF212121)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Settings",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    }
                }

                // Content
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    ) {
                        Text(
                            text = "Logout",
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }
}

