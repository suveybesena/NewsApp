package com.suveybesena.retrofitcoroutines.data.model


import androidx.room.ColumnInfo

data class Source(
    @ColumnInfo(name ="id" )
    val id: Any?,
    @ColumnInfo(name ="name" )
    val name: String?
)