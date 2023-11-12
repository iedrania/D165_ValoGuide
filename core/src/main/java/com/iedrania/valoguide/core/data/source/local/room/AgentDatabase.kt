package com.iedrania.valoguide.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.iedrania.valoguide.core.data.source.local.entity.AgentEntity
import com.iedrania.valoguide.core.utils.AbilityListConverter

@Database(entities = [AgentEntity::class], version = 1, exportSchema = false)
@TypeConverters(AbilityListConverter::class)
abstract class AgentDatabase : RoomDatabase() {
    abstract fun agentDao(): AgentDao
}