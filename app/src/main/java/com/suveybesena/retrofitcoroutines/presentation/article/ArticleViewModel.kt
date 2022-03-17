package com.suveybesena.retrofitcoroutines.presentation.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suveybesena.retrofitcoroutines.data.models.Article
import com.suveybesena.retrofitcoroutines.data.repository.NewsRepository
import kotlinx.coroutines.launch

class ArticleViewModel (
    val newsRepository: NewsRepository
        ) :ViewModel(){

    fun saveArticle(article: Article) = viewModelScope.launch {
        newsRepository.upsert(article)
    }

}