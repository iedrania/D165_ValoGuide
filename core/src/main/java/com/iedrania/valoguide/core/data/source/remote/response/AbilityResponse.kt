package com.iedrania.valoguide.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class AbilityResponse(
    @field:SerializedName("displayName")
    val displayName: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("displayIcon")
    val displayIcon: String? = null
)
