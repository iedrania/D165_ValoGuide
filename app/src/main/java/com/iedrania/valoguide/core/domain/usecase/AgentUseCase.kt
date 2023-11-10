package com.iedrania.valoguide.core.domain.usecase

import androidx.lifecycle.LiveData
import com.iedrania.valoguide.core.data.Resource
import com.iedrania.valoguide.core.domain.model.Agent

interface AgentUseCase {
    fun getAllAgent(): LiveData<Resource<List<Agent>>>
    fun getFavoriteAgent(): LiveData<List<Agent>>
    fun setFavoriteAgent(agent: Agent, state: Boolean)
}