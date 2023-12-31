package com.iedrania.valoguide.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Agent(
    val uuid: String,
    val displayName: String,
    val description: String,
    val fullPortrait: String,
    val isFavorite: Boolean,
    val backgroundGradientColors: String,
    val role: String,
    val abilities: List<Ability>
) : Parcelable
