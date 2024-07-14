package com.maverick.groww.di

import com.maverick.groww.owner.repo.TopGainerLoserRepository
import com.maverick.groww.utlis.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepoModule {

    @Provides
    @Singleton
    fun provideTopGainerLoserRepository(remoteDataSource: RemoteDataSource):TopGainerLoserRepository{
        return TopGainerLoserRepository(remoteDataSource = remoteDataSource)
    }
}