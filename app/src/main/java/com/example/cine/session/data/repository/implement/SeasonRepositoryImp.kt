package com.example.cine.session.data.repository.implement

import com.example.cine.session.data.model.SeasonInfo
import com.example.cine.session.data.remote.SeasonRemoteDataSource
import com.example.cine.session.data.repository.SeasonRepository
import javax.inject.Inject

class SeasonRepositoryImp @Inject constructor(
    private val seasonRemoteDataSource: SeasonRemoteDataSource
) : SeasonRepository {

    override suspend fun getDetailsSeason(serieId: Int, seasonNumber: Int): SeasonInfo =
        seasonRemoteDataSource.getDetailsSeason(serieId, seasonNumber).getOrThrow()
}