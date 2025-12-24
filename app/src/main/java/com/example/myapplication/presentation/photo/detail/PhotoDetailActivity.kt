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
import com.example.myapplication.uikit.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoDetailActivity : ComponentActivity() {

    companion object {
        const val EXTRA_PHOTO_ID = "extra_photo_id"
    }

    private val viewModel: PhotoDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val photoId = intent.getStringExtra(EXTRA_PHOTO_ID) ?: ""


        setContent {
            MyApplicationTheme {
                PhotoDetailScreen(
                    viewModel = viewModel,
                    photoId = photoId
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoDetailScreen(
    viewModel: PhotoDetailViewModel,
    photoId: String
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(photoId) {
        viewModel.loadPhotoDetail(photoId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Photo Detail") }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
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
                            .padding(16.dp)
                    )
                }
                uiState.photoDetail != null -> {
                    PhotoDetailContent(photoDetail = uiState.photoDetail!!)
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
            .padding(16.dp)
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

        Spacer(modifier = Modifier.height(16.dp))

        // Author
        Text(
            text = "Author",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = photoDetail.author,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Photo ID
        InfoRow(label = "Photo ID", value = photoDetail.id)

        Spacer(modifier = Modifier.height(8.dp))

        // Dimensions
        InfoRow(
            label = "Dimensions",
            value = "${photoDetail.width} x ${photoDetail.height}"
        )

        Spacer(modifier = Modifier.height(8.dp))

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
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PhotoDetailContentPreview() {
    MyApplicationTheme {
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

@Preview(showBackground = true)
@Composable
fun InfoRowPreview() {
    MyApplicationTheme {
        InfoRow(
            label = "Photo ID",
            value = "12345"
        )
    }
}
