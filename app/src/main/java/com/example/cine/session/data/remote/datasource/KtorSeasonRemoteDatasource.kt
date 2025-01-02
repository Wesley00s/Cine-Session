package com.example.cine.session.data.remote.datasource

import android.util.Log
import com.example.cine.session.BuildConfig
import com.example.cine.session.data.model.SeasonInfo
import com.example.cine.session.data.remote.SeasonRemoteDataSource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import javax.inject.Inject

class KtorSeasonRemoteDatasource @Inject constructor(
    private val httpClient: HttpClient
) : SeasonRemoteDataSource {
    companion object {
        const val BASE_URL = BuildConfig.BASE_URL
        const val TOKEN_API = BuildConfig.API_KEY
    }

    override suspend fun getDetailsSeason(
        id: Int,
        seasonNumber: Int
    ): Result<SeasonInfo> = try {
        val response = httpClient
            .get("${BASE_URL}/tv/$id/season/$seasonNumber?language=en-US") {
                header("Authorization", "Bearer $TOKEN_API")
                header("accept", "application/json")
            }
            .body<SeasonInfo>()
        Log.d("getDetailsSeason", "Response: $response")

        Result.success(response)
    } catch (e: Exception) {
        Log.e("getDetailsSeason", "Error: ${e.message}", e)
        Result.failure(e)
    }
}