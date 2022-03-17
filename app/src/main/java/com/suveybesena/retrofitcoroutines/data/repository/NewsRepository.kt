package com.suveybesena.retrofitcoroutines.data.repository

import com.suveybesena.retrofitcoroutines.data.remote.api.RetrofitInstance
import com.suveybesena.retrofitcoroutines.data.database.ArticleDatabase
import com.suveybesena.retrofitcoroutines.data.models.Article

class NewsRepository(
    val db: ArticleDatabase
) {
    suspend fun getNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getNews(countryCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumaber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumaber)

    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    fun getSavedNews() = db.getArticleDao().getAllArticles()
    //suspend fun getSavedNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticles(article)

}