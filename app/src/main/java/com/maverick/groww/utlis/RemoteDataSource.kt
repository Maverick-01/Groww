package com.maverick.groww.utlis

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val webservices: Webservices) {

    suspend fun getTopGainerLoserData() = webservices.fetchTopGainerLoserData(url = URLs.GET_TOP_GAINER_LOSERS)
    suspend fun getCompanyDetail(symbol:String) = webservices.fetchCompanyDetail(url = URLs.GET_COMPANY_DETAIL, symbol = symbol)
    suspend fun getSearchStock(keyword:String) = webservices.fetchSearchedStock(url = URLs.GET_SEARCHED_STOCK, keyword = keyword)
}