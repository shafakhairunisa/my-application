package com.example.myapplication.presentation.photo.list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.data.remote.api.RetrofitClient
import com.example.myapplication.data.repository.PhotoRepositoryImpl
import com.example.myapplication.databinding.FragmentPhotoListBinding
import com.example.myapplication.domain.usecase.GetPhotoListUseCase
import com.example.myapplication.presentation.photo.detail.PhotoDetailActivity
import com.example.myapplication.presentation.photo.list.adapter.PhotoListAdapter
import com.example.myapplication.presentation.photo.list.viewmodel.PhotoListViewModel
import com.example.myapplication.presentation.photo.list.viewmodel.PhotoListViewModelFactory
import kotlinx.coroutines.launch

class PhotoListFragment : Fragment() {

    private var _binding: FragmentPhotoListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: PhotoListViewModel
    private lateinit var adapter: PhotoListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
            val intent = Intent(requireContext(), PhotoDetailActivity::class.java)
            intent.putExtra(PhotoDetailActivity.EXTRA_PHOTO_ID, photo.id)
            startActivity(intent)
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@PhotoListFragment.adapter
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

