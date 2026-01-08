package com.example.myapplication.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.presentation.login.LoginActivity
import com.example.myapplication.presentation.photo.list.PhotoListFragment
import com.example.myapplication.presentation.setting.SettingScreen
import com.example.myapplication.presentation.setting.SettingViewModel
import com.example.myapplication.uikit.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val settingViewModel: SettingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val isDarkMode by settingViewModel.isDarkMode.collectAsState()

            // handle theme switching
            LaunchedEffect(isDarkMode) {
                val nightMode = if (isDarkMode) {
                    AppCompatDelegate.MODE_NIGHT_YES
                } else {
                    AppCompatDelegate.MODE_NIGHT_NO
                }

                if (AppCompatDelegate.getDefaultNightMode() != nightMode) {
                    AppCompatDelegate.setDefaultNightMode(nightMode)
                }
            }

            MyApplicationTheme(darkTheme = isDarkMode) {
                MainScreen(
                    activity = this,
                    settingViewModel = settingViewModel
                )
            }
        }

        // logout handler
        lifecycleScope.launch {
            settingViewModel.logoutEvent.collect {
                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    activity: AppCompatActivity,
    settingViewModel: SettingViewModel
) {
    var selectedTab by rememberSaveable { mutableStateOf(0) }

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
                1 -> SettingScreen(viewModel = settingViewModel)
            }
        }
    }
}

@Composable
fun PhotoListFragmentContainer(activity: AppCompatActivity) {
    AndroidView(
        factory = { context ->
            FragmentContainerView(context).apply {
                id = R.id.photo_list_fragment_container
            }
        },
        update = { view ->
            // check if activity still alive before doing fragment transaction
            if (!activity.isDestroyed && !activity.isFinishing) {
                val fragmentManager = activity.supportFragmentManager
                val existingFragment = fragmentManager.findFragmentByTag("PhotoListFragment")
                // add fragment if not exists yet
                if (existingFragment == null) {
                    try {
                        fragmentManager.beginTransaction()
                            .replace(view.id, PhotoListFragment(), "PhotoListFragment")
                            .commitNowAllowingStateLoss()
                    } catch (_: IllegalStateException) {
                        // activity state already saved, skip
                    }
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    )

    DisposableEffect(Unit) {
        onDispose {
            // cleanup fragment when leaving
            if (!activity.isDestroyed && !activity.isFinishing) {
                try {
                    val fragmentManager = activity.supportFragmentManager
                    val fragment = fragmentManager.findFragmentByTag("PhotoListFragment")
                    if (fragment != null && fragment.isAdded) {
                        fragmentManager.beginTransaction()
                            .remove(fragment)
                            .commitNowAllowingStateLoss()
                    }
                } catch (_: IllegalStateException) {
                    // state saved, ignore
                }
            }
        }
    }
}