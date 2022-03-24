package com.suveybesena.retrofitcoroutines.presentation.savednews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suveybesena.retrofitcoroutines.data.model.Article
import com.suveybesena.retrofitcoroutines.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedNewsViewModel @Inject constructor(val newsRepository: NewsRepository) : ViewModel() {

    fun saveArticle(article: Article) = viewModelScope.launch {
        newsRepository.upsert(article)
    }
    fun getSavedNews() = newsRepository.getSavedNews()

    fun deleteArticle(article: Article) = viewModelScope.launch {
        newsRepository.deleteArticle(article)
    }


}