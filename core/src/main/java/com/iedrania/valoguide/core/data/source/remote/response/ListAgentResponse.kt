package com.iedrania.valoguide.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListAgentResponse(
    @field:SerializedName("data")
    val data: List<AgentResponse>
)
