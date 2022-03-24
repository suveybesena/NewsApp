package com.suveybesena.retrofitcoroutines.data.repository


import com.suveybesena.retrofitcoroutines.data.database.ArticleDao
import com.suveybesena.retrofitcoroutines.data.model.Article
import com.suveybesena.retrofitcoroutines.data.remote.api.ArticleAPI
import javax.inject.Inject

class NewsRepository @Inject constructor(
    val db: ArticleDao,
    val api: ArticleAPI
) {

    suspend fun getNews(countryCode: String, pageNumber: Int) =
        api.getNews(countryCode, pageNumber)

    suspend fun upsert(article: Article) = db.upsert(article)

    fun getSavedNews() = db.getAllArticles()

    suspend fun deleteArticle(article: Article) = db.deleteArticles(article)

}