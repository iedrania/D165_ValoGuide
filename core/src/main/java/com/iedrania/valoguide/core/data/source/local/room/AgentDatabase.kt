package com.iedrania.valoguide.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.iedrania.valoguide.core.data.source.local.entity.AgentEntity

@Database(entities = [AgentEntity::class], version = 1, exportSchema = false)
abstract class AgentDatabase : RoomDatabase() {
    abstract fun agentDao(): AgentDao
}