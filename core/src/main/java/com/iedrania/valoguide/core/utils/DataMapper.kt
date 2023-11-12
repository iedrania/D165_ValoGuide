package com.iedrania.valoguide.core.utils

import com.iedrania.valoguide.core.data.source.local.entity.AbilityEntity
import com.iedrania.valoguide.core.data.source.local.entity.AgentEntity
import com.iedrania.valoguide.core.data.source.remote.response.AbilityResponse
import com.iedrania.valoguide.core.data.source.remote.response.AgentResponse
import com.iedrania.valoguide.core.domain.model.Ability
import com.iedrania.valoguide.core.domain.model.Agent

object DataMapper {
    fun mapResponsesToEntities(input: List<AgentResponse>): List<AgentEntity> {
        val agentList = ArrayList<AgentEntity>()
        input.map {
            val agent = AgentEntity(
                uuid = it.uuid,
                displayName = it.displayName,
                description = it.description,
                fullPortrait = it.fullPortrait,
                isFavorite = false,
                backgroundGradientColors = it.backgroundGradientColors[1].dropLast(2),
                role = it.role.displayName,
                abilities = mapAbilityResponsesToEntities(it.abilities)
            )
            agentList.add(agent)
        }
        return agentList
    }

    private fun mapAbilityResponsesToEntities(abilities: List<AbilityResponse>): List<AbilityEntity> =
        abilities.map {
            AbilityEntity(
                displayName = it.displayName,
                description = it.description,
                displayIcon = it.displayIcon
            )
        }

    fun mapEntitiesToDomain(input: List<AgentEntity>): List<Agent> = input.map {
        Agent(
            uuid = it.uuid,
            description = it.description,
            displayName = it.displayName,
            fullPortrait = it.fullPortrait,
            isFavorite = it.isFavorite,
            backgroundGradientColors = it.backgroundGradientColors,
            role = it.role,
            abilities = mapAbilityEntitiesToDomain(it.abilities)
        )
    }

    private fun mapAbilityEntitiesToDomain(abilities: List<AbilityEntity>): List<Ability> =
        abilities.map {
            Ability(
                displayName = it.displayName,
                description = it.description,
                displayIcon = it.displayIcon
            )
        }

    fun mapDomainToEntity(input: Agent) = AgentEntity(
        uuid = input.uuid,
        description = input.description,
        displayName = input.displayName,
        fullPortrait = input.fullPortrait,
        isFavorite = input.isFavorite,
        backgroundGradientColors = input.backgroundGradientColors,
        role = input.role,
        abilities = mapAbilityDomainToEntities(input.abilities)
    )

    private fun mapAbilityDomainToEntities(abilities: List<Ability>) = abilities.map {
        AbilityEntity(
            displayName = it.displayName,
            description = it.description,
            displayIcon = it.displayIcon
        )
    }
}
