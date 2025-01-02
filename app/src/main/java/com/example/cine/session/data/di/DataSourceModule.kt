package com.example.cine.session.data.di

import com.example.cine.session.data.remote.MovieRemoteDataSource
import com.example.cine.session.data.remote.SeasonRemoteDataSource
import com.example.cine.session.data.remote.SerieRemoteDataSource
import com.example.cine.session.data.remote.datasource.KtorMovieRemoteDatasource
import com.example.cine.session.data.remote.datasource.KtorSeasonRemoteDatasource
import com.example.cine.session.data.remote.datasource.KtorSerieRemoteDatasource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    @Singleton
    fun bindMovieRemoteDataSource(
        ktorMovieRemoteDataSource: KtorMovieRemoteDatasource
    ): MovieRemoteDataSource

    @Binds
    @Singleton
    fun bindSerieRemoteDataSource(
        ktorSerieRemoteDataSource: KtorSerieRemoteDatasource
    ): SerieRemoteDataSource

    @Binds
    @Singleton
    fun bindSeasonRemoteDataSource(
        ktorSeasonRemoteDataSource: KtorSeasonRemoteDatasource
    ): SeasonRemoteDataSource
}