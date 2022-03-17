package com.suveybesena.retrofitcoroutines.presentation.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.suveybesena.retrofitcoroutines.data.repository.NewsRepository

class ArticleViewModelProviderFactory(
    val newsRepository: NewsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ArticleViewModel(newsRepository) as T
    }
}
