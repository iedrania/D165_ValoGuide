package com.iedrania.valoguide.core.utils

import com.iedrania.valoguide.core.data.source.local.entity.AgentEntity
import com.iedrania.valoguide.core.data.source.remote.response.AgentResponse
import com.iedrania.valoguide.core.domain.model.Agent

object DataMapper {
    fun mapResponsesToEntities(input: List<AgentResponse>): List<AgentEntity> {
        val agentList = ArrayList<AgentEntity>()
        input.map {
            val agent = AgentEntity(
                uuid = it.uuid,
                displayName = it.displayName,
                description = it.description,
                displayIcon = it.displayIcon,
                fullPortrait = it.fullPortrait,
                isFavorite = false
            )
            agentList.add(agent)
        }
        return agentList
    }

    fun mapEntitiesToDomain(input: List<AgentEntity>): List<Agent> =
        input.map {
            Agent(
                uuid = it.uuid,
                description = it.description,
                displayName = it.displayName,
                displayIcon = it.displayIcon,
                fullPortrait = it.fullPortrait,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Agent) = AgentEntity(
        uuid = input.uuid,
        description = input.description,
        displayName = input.displayName,
        displayIcon = input.displayIcon,
        fullPortrait = input.fullPortrait,
        isFavorite = input.isFavorite
    )
}
