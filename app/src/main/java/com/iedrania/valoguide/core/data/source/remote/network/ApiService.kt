package com.iedrania.valoguide.core.data.source.remote.network

import com.iedrania.valoguide.core.data.source.remote.response.ListAgentResponse
import retrofit2.http.*

interface ApiService {
    @GET("agents")
    suspend fun getList(
        @Query("isPlayableCharacter") isPlayableCharacter: Boolean = true
    ): ListAgentResponse
}