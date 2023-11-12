package com.iedrania.valoguide.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class AbilityEntity(
    @ColumnInfo(name = "displayName")
    val displayName: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "displayIcon")
    val displayIcon: String?
)