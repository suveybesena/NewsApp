package com.suveybesena.retrofitcoroutines.presentation.savednews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suveybesena.retrofitcoroutines.data.models.Article
import com.suveybesena.retrofitcoroutines.data.repository.NewsRepository
import kotlinx.coroutines.launch

class SavedNewsViewModel(val newsRepository: NewsRepository) :ViewModel() {

    //val getSavedNews: MutableLiveData<List<Article>> = MutableLiveData()

    fun saveArticle(article: Article) = viewModelScope.launch {
        newsRepository.upsert(article)
    }

    fun getSavedNews() = newsRepository.getSavedNews()

    //fun getSavedNews () = viewModelScope.launch {
    //   newsRepository.getSavedNews().collect {
    //       getSavedNews.value = it
    //   }
    //}

    fun deleteArticle(article: Article) = viewModelScope.launch {
        newsRepository.deleteArticle(article)
    }


  
}