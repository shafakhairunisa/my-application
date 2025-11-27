package com.example.myapplication.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.data.local.PreferencesManager
import com.example.myapplication.data.repository.AuthRepositoryImpl
import com.example.myapplication.domain.usecase.LogoutUseCase
import com.example.myapplication.presentation.photo.list.PhotoListFragment
import com.example.myapplication.presentation.login.LoginActivity
import com.example.myapplication.presentation.setting.SettingScreen
import com.example.myapplication.presentation.setting.SettingViewModel
import com.example.myapplication.presentation.setting.SettingViewModelFactory
import com.example.myapplication.uitkit.MyApplicationTheme

class MainActivity : FragmentActivity() {

    private lateinit var settingViewModel: SettingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize ViewModel for Settings
        val preferencesManager = PreferencesManager(this)
        val authRepository = AuthRepositoryImpl(preferencesManager)
        val logoutUseCase = LogoutUseCase(authRepository)
        val factory = SettingViewModelFactory(logoutUseCase)
        settingViewModel = ViewModelProvider(this, factory)[SettingViewModel::class.java]

        setContent {
            MyApplicationTheme {
                MainScreen(
                    activity = this,
                    settingViewModel = settingViewModel,
                    onLogout = {
                        val intent = Intent(this, LoginActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                        finish()
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    activity: FragmentActivity,
    settingViewModel: SettingViewModel,
    onLogout: () -> Unit
) {
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            ) {
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(android.R.drawable.ic_menu_gallery),
                            contentDescription = "List"
                        )
                    },
                    label = { Text("List of Picsum") },
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 }
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(android.R.drawable.ic_menu_preferences),
                            contentDescription = "Settings"
                        )
                    },
                    label = { Text("Setting") },
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 }
                )
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (selectedTab) {
                0 -> PhotoListFragmentContainer(activity = activity)
                1 -> SettingScreen(
                    viewModel = settingViewModel,
                    onLogoutClick = onLogout
                )
            }
        }
    }
}

@Composable
fun PhotoListFragmentContainer(activity: FragmentActivity) {
    AndroidView(
        factory = { context ->
            FragmentContainerView(context).apply {
                id = R.id.photo_list_fragment_container
            }
        },
        update = { view ->
            // Add fragment only if not already added
            val fragmentManager = activity.supportFragmentManager
            val existingFragment = fragmentManager.findFragmentById(R.id.photo_list_fragment_container)
            if (existingFragment == null) {
                fragmentManager.beginTransaction()
                    .replace(R.id.photo_list_fragment_container, PhotoListFragment())
                    .commitNow()
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MyApplicationTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            var selectedTab by remember { mutableStateOf(0) }

            Scaffold(
                bottomBar = {
                    NavigationBar {
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    painter = painterResource(android.R.drawable.ic_menu_gallery),
                                    contentDescription = "List"
                                )
                            },
                            label = { Text("List of Picsum") },
                            selected = selectedTab == 0,
                            onClick = { selectedTab = 0 }
                        )
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    painter = painterResource(android.R.drawable.ic_menu_preferences),
                                    contentDescription = "Settings"
                                )
                            },
                            label = { Text("Setting") },
                            selected = selectedTab == 1,
                            onClick = { selectedTab = 1 }
                        )
                    }
                }
            ) { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)) {
                    Text("Content Area")
                }
            }
        }
    }
}