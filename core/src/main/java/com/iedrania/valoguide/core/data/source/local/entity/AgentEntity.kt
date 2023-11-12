package com.iedrania.valoguide.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.iedrania.valoguide.core.utils.AbilityListConverter

@Entity(tableName = "agent")
data class AgentEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "uuid")
    var uuid: String,

    @ColumnInfo(name = "displayName")
    var displayName: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "fullPortrait")
    var fullPortrait: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false,

    @ColumnInfo(name = "backgroundGradientColors")
    var backgroundGradientColors: String,

    @ColumnInfo(name = "role")
    var role: String,

    @TypeConverters(AbilityListConverter::class)
    @ColumnInfo(name = "abilities")
    var abilities: List<AbilityEntity>
)
