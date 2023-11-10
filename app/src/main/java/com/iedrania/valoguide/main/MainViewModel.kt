package com.iedrania.valoguide.main

import androidx.lifecycle.ViewModel
import com.iedrania.valoguide.core.domain.usecase.AgentUseCase

class MainViewModel(agentUseCase: AgentUseCase) : ViewModel() {
    val agent = agentUseCase.getAllAgent()
}
