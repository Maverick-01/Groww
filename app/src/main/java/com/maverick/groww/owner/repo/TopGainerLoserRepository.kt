package com.maverick.groww.owner.repo

import com.maverick.groww.utlis.RemoteDataSource
import javax.inject.Inject

class TopGainerLoserRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) {

    suspend fun getTopGainerLoserData() = remoteDataSource.getTopGainerLoserData()
    suspend fun getCompanyDetail(symbol:String) = remoteDataSource.getCompanyDetail(symbol = symbol)
    suspend fun getSearchStock(keyword:String) = remoteDataSource.getSearchStock(keyword = keyword)
}