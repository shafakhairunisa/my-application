package com.example.myapplication.presentation.photo.detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.myapplication.domain.model.PhotoDetailItem
import com.example.myapplication.presentation.photo.detail.viewmodel.PhotoDetailViewModel
import com.example.myapplication.presentation.setting.SettingViewModel
import com.example.myapplication.uikit.AppBarHeight
import com.example.myapplication.uikit.MyApplicationTheme
import com.example.myapplication.uikit.Spacing
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoDetailActivity : ComponentActivity() {

    companion object {
        const val EXTRA_PHOTO_ID = "extra_photo_id"
    }

    private val viewModel: PhotoDetailViewModel by viewModels()
    private val settingViewModel: SettingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val photoId = intent.getStringExtra(EXTRA_PHOTO_ID) ?: ""


        setContent {
            val isDarkMode by settingViewModel.isDarkMode.collectAsState()

            MyApplicationTheme(darkTheme = isDarkMode) {
                PhotoDetailScreen(
                    viewModel = viewModel,
                    photoId = photoId
                )
            }
        }
    }
}

@Composable
fun PhotoDetailScreen(
    viewModel: PhotoDetailViewModel,
    photoId: String
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(photoId) {
        viewModel.loadPhotoDetail(photoId)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Custom Toolbar matching Settings screen
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(AppBarHeight),
                color = MaterialTheme.colorScheme.primary
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Photo Detail",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
            }

            // Content
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                when {
                    uiState.isLoading -> {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    uiState.error != null -> {
                        Text(
                            text = uiState.error ?: "Unknown error",
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(Spacing.m)
                        )
                    }
                    uiState.photoDetail != null -> {
                        PhotoDetailContent(photoDetail = uiState.photoDetail!!)
                    }
                }
            }
        }
    }
}

@Composable
fun PhotoDetailContent(photoDetail: PhotoDetailItem) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(Spacing.m)
    ) {
        // Image
        AsyncImage(
            model = photoDetail.downloadUrl,
            contentDescription = "Photo by ${photoDetail.author}",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(Spacing.m))

        // Author
        Text(
            text = "Author",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = photoDetail.author,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(Spacing.m))

        // Photo ID
        InfoRow(label = "Photo ID", value = photoDetail.id)

        Spacer(modifier = Modifier.height(Spacing.xs))

        // Dimensions
        InfoRow(
            label = "Dimensions",
            value = "${photoDetail.width} x ${photoDetail.height}"
        )

        Spacer(modifier = Modifier.height(Spacing.xs))

        // URL
        Text(
            text = "URL",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = photoDetail.url,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Column {
        Text(
            text = label,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "Light Mode")
@Composable
fun PhotoDetailContentPreview() {
    MyApplicationTheme(darkTheme = false) {
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
                        .height(AppBarHeight),
                    color = MaterialTheme.colorScheme.primary
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Photo Detail",
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    }
                }

                // Content
                PhotoDetailContent(
                    photoDetail = PhotoDetailItem(
                        id = "1",
                        author = "John Doe",
                        width = 1920,
                        height = 1080,
                        url = "https://unsplash.com/photos/sample",
                        downloadUrl = "https://picsum.photos/id/1/1920/1080"
                    )
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Dark Mode",
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PhotoDetailContentDarkPreview() {
    MyApplicationTheme(darkTheme = true) {
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
                        .height(AppBarHeight),
                    color = MaterialTheme.colorScheme.primary
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Photo Detail",
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    }
                }

                // Content
                PhotoDetailContent(
                    photoDetail = PhotoDetailItem(
                        id = "1",
                        author = "John Doe",
                        width = 1920,
                        height = 1080,
                        url = "https://unsplash.com/photos/sample",
                        downloadUrl = "https://picsum.photos/id/1/1920/1080"
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Light Mode")
@Composable
fun InfoRowPreview() {
    MyApplicationTheme(darkTheme = false) {
        Surface(color = MaterialTheme.colorScheme.background) {
            InfoRow(
                label = "Photo ID",
                value = "12345"
            )
        }
    }
}

@Preview(
    showBackground = true,
    name = "Dark Mode",
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun InfoRowDarkPreview() {
    MyApplicationTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.background) {
            InfoRow(
                label = "Photo ID",
                value = "12345"
            )
        }
    }
}