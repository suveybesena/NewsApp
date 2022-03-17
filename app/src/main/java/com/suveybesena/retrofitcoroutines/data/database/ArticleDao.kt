package com.suveybesena.retrofitcoroutines.data.database


import androidx.lifecycle.LiveData
import androidx.room.*
import com.suveybesena.retrofitcoroutines.data.models.Article
import kotlinx.coroutines.flow.Flow


@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article): Long

    @Query("SELECT * FROM articles")
     fun getAllArticles(): LiveData<List<Article>>
     //suspend fun getAllArticles(): Flow<List<Article>>
     //TODO flow

    @Delete
    suspend fun deleteArticles(article: Article)

}