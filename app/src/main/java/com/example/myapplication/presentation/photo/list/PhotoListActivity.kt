package com.example.myapplication.presentation.photo.list

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.data.remote.api.RetrofitClient
import com.example.myapplication.data.repository.PhotoRepositoryImpl
import com.example.myapplication.databinding.ActivityPhotoListBinding
import com.example.myapplication.domain.usecase.GetPhotoListUseCase
import com.example.myapplication.presentation.photo.detail.PhotoDetailActivity
import com.example.myapplication.presentation.photo.list.adapter.PhotoListAdapter
import com.example.myapplication.presentation.photo.list.viewmodel.PhotoListViewModel
import com.example.myapplication.presentation.photo.list.viewmodel.PhotoListViewModelFactory
import kotlinx.coroutines.launch

class PhotoListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhotoListBinding
    private lateinit var viewModel: PhotoListViewModel
    private lateinit var adapter: PhotoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize ViewModel with dependencies
        setupViewModel()
        setupRecyclerView()
        observeViewModel()

        viewModel.loadPhotoList()
    }

    private fun setupViewModel() {
        val repository = PhotoRepositoryImpl(RetrofitClient.apiService)
        val useCase = GetPhotoListUseCase(repository)
        val factory = PhotoListViewModelFactory(useCase)
        viewModel = ViewModelProvider(this, factory)[PhotoListViewModel::class.java]
    }

    private fun setupRecyclerView() {
        adapter = PhotoListAdapter { photo ->
            val intent = Intent(this, PhotoDetailActivity::class.java)
            intent.putExtra(PhotoDetailActivity.EXTRA_PHOTO_ID, photo.id)
            startActivity(intent)
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@PhotoListActivity)
            adapter = this@PhotoListActivity.adapter
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.uiState.collect { uiState ->
                binding.progressBar.visibility = if (uiState.isLoading) View.VISIBLE else View.GONE

                uiState.photoList?.let { photos ->
                    adapter.submitList(photos)
                }

                binding.tvError.visibility = if (uiState.error != null) View.VISIBLE else View.GONE
                binding.tvError.text = uiState.error
            }
        }
    }
}

