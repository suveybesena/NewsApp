package com.suveybesena.retrofitcoroutines.presentation.savednews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.suveybesena.retrofitcoroutines.data.repository.NewsRepository

class SavedNewsModelProviderFactory (
    val newsRepository: NewsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SavedNewsViewModel(newsRepository) as T
    }
}
