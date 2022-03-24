package com.suveybesena.retrofitcoroutines.data.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(
    tableName = "articles"
)
data class Article(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="id" )
    var id: Int? = null,
    @ColumnInfo(name ="author" )
    val author: String?,
    @ColumnInfo(name ="content" )
    val content: String?,
    @ColumnInfo(name ="description" )
    val description: String?,
    @ColumnInfo(name ="publishedAt" )
    val publishedAt: String?,
    @ColumnInfo(name ="source")
    val source: Source?,
    @ColumnInfo(name ="title" )
    val title: String?,
    @ColumnInfo(name ="url" )
    val url: String?,
    @ColumnInfo(name ="urlToImage" )
    val urlToImage: String?
) : Serializable