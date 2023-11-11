package com.iedrania.valoguide.core.data.source.remote

import android.util.Log
import com.iedrania.valoguide.core.data.source.remote.network.ApiResponse
import com.iedrania.valoguide.core.data.source.remote.network.ApiService
import com.iedrania.valoguide.core.data.source.remote.response.AgentResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getAllAgent(): Flow<ApiResponse<List<AgentResponse>>> {
        return flow {
            try {
                val response = apiService.getList()
                val dataArray = response.data
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}
