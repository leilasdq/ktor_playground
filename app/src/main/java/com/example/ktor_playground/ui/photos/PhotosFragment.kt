package com.example.ktor_playground.ui.photos

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotosFragment: Fragment() {

    val viewModel: PhotosViewModel by viewModels()
}