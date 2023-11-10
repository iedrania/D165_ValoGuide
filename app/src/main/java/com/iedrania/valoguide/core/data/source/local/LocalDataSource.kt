package com.iedrania.valoguide.core.data.source.local

import androidx.lifecycle.LiveData
import com.iedrania.valoguide.core.data.source.local.entity.AgentEntity
import com.iedrania.valoguide.core.data.source.local.room.AgentDao

class LocalDataSource private constructor(private val agentDao: AgentDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(agentDao: AgentDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(agentDao)
            }
    }

    fun getAllAgent(): LiveData<List<AgentEntity>> = agentDao.getAllAgent()

    fun getFavoriteAgent(): LiveData<List<AgentEntity>> = agentDao.getFavoriteAgent()

    fun insertAgent(agentList: List<AgentEntity>) = agentDao.insertAgent(agentList)

    fun setFavoriteAgent(agent: AgentEntity, newState: Boolean) {
        agent.isFavorite = newState
        agentDao.updateFavoriteAgent(agent)
    }
}