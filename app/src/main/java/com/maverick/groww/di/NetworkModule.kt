package com.maverick.groww.di

import android.content.Context
import com.google.gson.GsonBuilder
import com.maverick.groww.data.TopGainerLoserMaster
import com.maverick.groww.utlis.Constants.API_KEY
import com.maverick.groww.utlis.Constants.BASE_URL
import com.maverick.groww.utlis.Constants.hasNetwork
import com.maverick.groww.utlis.RemoteDataSource
import com.maverick.groww.utlis.Webservices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideWebService(retrofit: Retrofit): Webservices {
        return retrofit.create(Webservices::class.java)
    }

    @Provides
    @Singleton
    fun provideCache(@ApplicationContext context: Context): Cache {
        val cacheSize = 10 * 1024 * 1024 // 10 MB
        return Cache(File(context.cacheDir, "http_cache"), cacheSize.toLong())
    }

    @Provides
    @Singleton
    @Named("CacheInterceptor")
    fun provideCacheInterceptor(@ApplicationContext context: Context): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            request = if (hasNetwork(context))
                request.newBuilder().header("Cache-Control", "public, max-age=7200")
                    .header("X-Cache", "MISS")
                    .build() // 2 hours
            else
                request.newBuilder().header(
                    "Cache-Control",
                    "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                )// 1 week
                    .header("X-Cache", "HIT")
                    .build()
            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    @Named("ApiKeyInterceptor")
    fun provideApiKeyInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request().newBuilder()
            val originalHttpUrl = chain.request().url()
            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("apikey", API_KEY).build()
            request.url(url)
            chain.proceed(request.build())
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        cache: Cache,
        @Named("CacheInterceptor") cacheInterceptor: Interceptor,
        @Named("ApiKeyInterceptor") apiKeyInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(apiKeyInterceptor)
            .addInterceptor(cacheInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitService(retrofit: Retrofit): TopGainerLoserMaster {
        return retrofit.create(TopGainerLoserMaster::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(webservices: Webservices): RemoteDataSource {
        return RemoteDataSource(webservices = webservices)
    }
}