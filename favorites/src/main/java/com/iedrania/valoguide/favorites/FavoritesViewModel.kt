package com.iedrania.valoguide.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.iedrania.valoguide.core.domain.usecase.AgentUseCase

class FavoritesViewModel(agentUseCase: AgentUseCase) : ViewModel() {
    val favoriteAgent = agentUseCase.getFavoriteAgent().asLiveData()
}