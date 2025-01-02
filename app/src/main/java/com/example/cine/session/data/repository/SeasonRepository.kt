package com.example.cine.session.data.repository

import com.example.cine.session.data.model.SeasonInfo

interface SeasonRepository {
    suspend fun getDetailsSeason(serieId: Int, seasonNumber: Int): SeasonInfo
}