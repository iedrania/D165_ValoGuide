package com.iedrania.valoguide.core.domain.repository

import com.iedrania.valoguide.core.data.Resource
import com.iedrania.valoguide.core.domain.model.Agent
import kotlinx.coroutines.flow.Flow

interface IAgentRepository {
    fun getAllAgent(): Flow<Resource<List<Agent>>>

    fun getFavoriteAgent(): Flow<List<Agent>>

    fun setFavoriteAgent(agent: Agent, state: Boolean)
}