package com.example.cine.session.data.di

import com.example.cine.session.data.repository.MovieRepository
import com.example.cine.session.data.repository.SeriesRepository
import com.example.cine.session.data.repository.implement.MovieRepositoryImp
import com.example.cine.session.data.repository.implement.SeriesRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {
    @Binds
    fun bindMovieRepository(
        detailsMovieRepositoryImplement: MovieRepositoryImp
    ): MovieRepository

    @Binds
    fun bindSerieRepository(
        detailsSeriesRepositoryImplement: SeriesRepositoryImp
    ): SeriesRepository
}