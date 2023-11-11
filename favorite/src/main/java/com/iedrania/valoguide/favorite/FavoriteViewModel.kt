package com.iedrania.valoguide.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.iedrania.valoguide.core.domain.usecase.AgentUseCase

class FavoriteViewModel(agentUseCase: AgentUseCase) : ViewModel() {
    val favoriteAgent = agentUseCase.getFavoriteAgent().asLiveData()
}