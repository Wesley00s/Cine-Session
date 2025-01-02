package com.example.cine.session.data.di

import com.example.cine.session.data.repository.MovieRepository
import com.example.cine.session.data.repository.SeasonRepository
import com.example.cine.session.data.repository.SerieRepository
import com.example.cine.session.data.repository.implement.MovieRepositoryImp
import com.example.cine.session.data.repository.implement.SeasonRepositoryImp
import com.example.cine.session.data.repository.implement.SerieRepositoryImp
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
        detailsSeriesRepositoryImplement: SerieRepositoryImp
    ): SerieRepository

    @Binds
    fun bindSeasonRepository(
        detailsSeasonRepositoryImplement: SeasonRepositoryImp
    ): SeasonRepository
}