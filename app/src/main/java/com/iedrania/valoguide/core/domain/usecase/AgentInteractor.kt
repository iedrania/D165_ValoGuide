package com.iedrania.valoguide.core.domain.usecase

import com.iedrania.valoguide.core.domain.model.Agent
import com.iedrania.valoguide.core.domain.repository.IAgentRepository

class AgentInteractor(private val agentRepository: IAgentRepository): AgentUseCase {
    override fun getAllAgent() = agentRepository.getAllAgent()

    override fun getFavoriteAgent() = agentRepository.getFavoriteAgent()

    override fun setFavoriteAgent(agent: Agent, state: Boolean) = agentRepository.setFavoriteAgent(agent, state)
}