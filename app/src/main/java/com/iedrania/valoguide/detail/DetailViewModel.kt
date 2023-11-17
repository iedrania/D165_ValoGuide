package com.iedrania.valoguide.detail

import androidx.lifecycle.ViewModel
import com.iedrania.valoguide.core.domain.model.Agent
import com.iedrania.valoguide.core.domain.usecase.AgentUseCase

class DetailViewModel(private val agentUseCase: AgentUseCase) : ViewModel() {
    fun setFavoriteAgent(agent: Agent, newStatus: Boolean) =
        agentUseCase.setFavoriteAgent(agent, newStatus)
}