package com.maverick.groww.owner.observer

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maverick.groww.data.CompanyDetail
import com.maverick.groww.data.SearchStock
import com.maverick.groww.data.TopGainerLoserMaster
import com.maverick.groww.owner.repo.TopGainerLoserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class TopGainerLoserViewModel @Inject constructor(private val repo: TopGainerLoserRepository) :
    ViewModel() {
    private val _getTopGainerLoserObservable = MutableLiveData<TopGainerLoserMaster>()
    val getTopGainerLoserObservable: LiveData<TopGainerLoserMaster> = _getTopGainerLoserObservable

    private val _getCompanyDetailObservable = MutableLiveData<Response<CompanyDetail>>()
    val getCompanyDetailObservable: LiveData<Response<CompanyDetail>> = _getCompanyDetailObservable

    private val _getSearchStockObservable = MutableLiveData<Response<SearchStock>>()
    val getSearchStockObservable: LiveData<Response<SearchStock>> = _getSearchStockObservable

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    fun getTopGainerLoserData() {
        _loading.value = true
        viewModelScope.launch {
            repo.getTopGainerLoserData().let { response ->
                if (response.isSuccessful) {
                    _getTopGainerLoserObservable.postValue(response.body())
                }
                _loading.postValue(false)
                val source = response.headers()["X-Cache"]
                val fromCache = source == "HIT"
                Log.d("MyViewModel", "Data fetched from ${if (fromCache) "cache" else "remote"}")
            }
        }
    }

    fun getCompanyDetail(symbol: String) {
        _loading.value = true
        viewModelScope.launch {
            repo.getCompanyDetail(symbol = symbol).let { response ->
                if (response.isSuccessful) {
                    _getCompanyDetailObservable.postValue(response)
                }
                _loading.postValue(false)
            }
        }
    }

    fun getSearchStock(keyword: String) {
        _loading.value = true
        viewModelScope.launch {
            repo.getSearchStock(keyword = keyword).let { response ->
                if (response.isSuccessful) {
                    _getSearchStockObservable.postValue(response)
                }
                _loading.postValue(false)
            }
        }
    }
}