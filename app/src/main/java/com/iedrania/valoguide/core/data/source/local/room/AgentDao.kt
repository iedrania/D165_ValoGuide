package com.iedrania.valoguide.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.iedrania.valoguide.core.data.source.local.entity.AgentEntity

@Dao
interface AgentDao {
    @Query("SELECT * FROM agent")
    fun getAllAgent(): LiveData<List<AgentEntity>>

    @Query("SELECT * FROM agent where isFavorite = 1")
    fun getFavoriteAgent(): LiveData<List<AgentEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAgent(agent: List<AgentEntity>)

    @Update
    fun updateFavoriteAgent(agent: AgentEntity)
}
