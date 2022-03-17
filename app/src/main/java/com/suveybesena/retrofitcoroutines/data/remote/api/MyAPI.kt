package com.suveybesena.retrofitcoroutines.data.remote.api

import com.suveybesena.retrofitcoroutines.data.models.NewsResponse
import com.suveybesena.retrofitcoroutines.common.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MyAPI {

    @GET("v2/top-headlines")
    suspend fun getNews(
        @Query("country")
        countryCode: String = "us",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY,
        ): Response<NewsResponse>

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY,

        ): Response<NewsResponse>

}