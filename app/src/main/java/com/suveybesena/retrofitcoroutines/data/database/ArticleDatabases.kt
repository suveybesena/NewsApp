package com.suveybesena.retrofitcoroutines.data.database


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.suveybesena.retrofitcoroutines.data.model.Article

@Database(
    entities = [Article::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao

}