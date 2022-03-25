package com.suveybesena.retrofitcoroutines.presentation.search


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suveybesena.retrofitcoroutines.data.model.NewsResponse
import com.suveybesena.retrofitcoroutines.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import com.suveybesena.retrofitcoroutines.common.Resources
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SearchNewsViewModel @Inject constructor( val newsRepository: NewsRepository) : ViewModel() {

    val searchNews: MutableLiveData<Resources<NewsResponse>> = MutableLiveData()
    val newsPage = 1

    fun searchNews(searchQuery: String) = viewModelScope.launch {
        searchNews.postValue(Resources.loading())
        val response = newsRepository.searchNews(searchQuery, newsPage)
        searchNews.postValue(handleSearchNewsResponse(response))
    }

    private fun handleSearchNewsResponse(response: Response<NewsResponse>): Resources<NewsResponse> {
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