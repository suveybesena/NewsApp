package com.suveybesena.retrofitcoroutines.data.model


import androidx.room.ColumnInfo

data class NewsResponse(
    @ColumnInfo(name ="articles" )
    val articles: List<Article>,
    @ColumnInfo(name ="status" )
    val status: String,
    @ColumnInfo(name ="totalResults" )
    val totalResults: Int
)