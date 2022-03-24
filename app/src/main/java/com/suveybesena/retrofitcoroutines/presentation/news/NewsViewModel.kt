package com.suveybesena.retrofitcoroutines.presentation.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suveybesena.retrofitcoroutines.data.model.NewsResponse
import com.suveybesena.retrofitcoroutines.data.repository.NewsRepository
import com.suveybesena.retrofitcoroutines.common.Resources
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class NewsViewModel
@Inject constructor(
    val newsRepository: NewsRepository
) : ViewModel() {

    val news: MutableLiveData<Resources<NewsResponse>> = MutableLiveData()
    val newsPage = 1

    init {
        news("us")
    }

    fun news(coutryCode: String) = viewModelScope.launch {
        news.postValue(Resources.loading())
        val response = newsRepository.getNews(coutryCode, newsPage)
        news.postValue(handleNewsResponse(response))
    }

    private fun handleNewsResponse(response: Response<NewsResponse>): Resources<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return resultResponse.let {
                    Resources.success(it)
                }
            }
        }
        return Resources.error(response.message())
    }


}