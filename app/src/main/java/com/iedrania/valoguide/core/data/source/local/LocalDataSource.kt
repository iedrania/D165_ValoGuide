package com.iedrania.valoguide.core.data.source.local

import com.iedrania.valoguide.core.data.source.local.entity.AgentEntity
import com.iedrania.valoguide.core.data.source.local.room.AgentDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource private constructor(private val agentDao: AgentDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(agentDao: AgentDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(agentDao)
            }
    }

    fun getAllAgent(): Flow<List<AgentEntity>> = agentDao.getAllAgent()

    fun getFavoriteAgent(): Flow<List<AgentEntity>> = agentDao.getFavoriteAgent()

    suspend fun insertAgent(agentList: List<AgentEntity>) = agentDao.insertAgent(agentList)

    fun setFavoriteAgent(agent: AgentEntity, newState: Boolean) {
        agent.isFavorite = newState
        agentDao.updateFavoriteAgent(agent)
    }
}