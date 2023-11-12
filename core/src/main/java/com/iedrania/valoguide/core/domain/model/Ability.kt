package com.iedrania.valoguide.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ability(
    val displayName: String,
    val description: String,
    val displayIcon: String?
) : Parcelable
