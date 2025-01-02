package com.example.cine.session.data.remote

import com.example.cine.session.data.model.SeasonInfo

interface SeasonRemoteDataSource {
    suspend fun getDetailsSeason(id: Int, seasonNumber: Int): Result<SeasonInfo>
}