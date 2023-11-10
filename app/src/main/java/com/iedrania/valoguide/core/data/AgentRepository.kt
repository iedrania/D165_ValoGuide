package com.iedrania.valoguide.core.data

import com.iedrania.valoguide.core.data.source.local.LocalDataSource
import com.iedrania.valoguide.core.data.source.remote.RemoteDataSource
import com.iedrania.valoguide.core.data.source.remote.network.ApiResponse
import com.iedrania.valoguide.core.data.source.remote.response.AgentResponse
import com.iedrania.valoguide.core.domain.model.Agent
import com.iedrania.valoguide.core.domain.repository.IAgentRepository
import com.iedrania.valoguide.core.utils.AppExecutors
import com.iedrania.valoguide.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AgentRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IAgentRepository {

    companion object {
        @Volatile
        private var instance: AgentRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): AgentRepository =
            instance ?: synchronized(this) {
                instance ?: AgentRepository(remoteData, localData, appExecutors)
            }
    }

    override fun getAllAgent(): Flow<Resource<List<Agent>>> =
        object : NetworkBoundResource<List<Agent>, List<AgentResponse>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Agent>> {
                return localDataSource.getAllAgent().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Agent>?): Boolean =
                true

            override suspend fun createCall(): Flow<ApiResponse<List<AgentResponse>>> =
                remoteDataSource.getAllAgent()

            override suspend fun saveCallResult(data: List<AgentResponse>) {
                val agentList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertAgent(agentList)
            }
        }.asFlow()

    override fun getFavoriteAgent(): Flow<List<Agent>> {
        return localDataSource.getFavoriteAgent().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteAgent(agent: Agent, state: Boolean) {
        val agentEntity = DataMapper.mapDomainToEntity(agent)
        appExecutors.diskIO().execute { localDataSource.setFavoriteAgent(agentEntity, state) }
    }
}