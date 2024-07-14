package com.maverick.groww.utlis

import com.maverick.groww.data.CompanyDetail
import com.maverick.groww.data.SearchStock
import com.maverick.groww.data.TopGainerLoserMaster
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface Webservices {

    @GET
    suspend fun fetchTopGainerLoserData(
        @Url url: String,
    ): Response<TopGainerLoserMaster>

    @GET
    suspend fun fetchCompanyDetail(
        @Url url: String,
        @Query("symbol") symbol: String,
    ): Response<CompanyDetail>

    @GET
    suspend fun fetchSearchedStock(
        @Url url: String,
        @Query("keywords") keyword: String,
    ): Response<SearchStock>
}