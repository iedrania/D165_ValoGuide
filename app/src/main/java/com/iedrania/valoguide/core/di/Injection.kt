package com.iedrania.valoguide.core.di

import android.content.Context
import com.iedrania.valoguide.core.data.AgentRepository
import com.iedrania.valoguide.core.data.source.local.LocalDataSource
import com.iedrania.valoguide.core.data.source.local.room.AgentDatabase
import com.iedrania.valoguide.core.data.source.remote.RemoteDataSource
import com.iedrania.valoguide.core.data.source.remote.network.ApiConfig
import com.iedrania.valoguide.core.domain.repository.IAgentRepository
import com.iedrania.valoguide.core.domain.usecase.AgentInteractor
import com.iedrania.valoguide.core.domain.usecase.AgentUseCase
import com.iedrania.valoguide.core.utils.AppExecutors

object Injection {
    private fun provideRepository(context: Context): IAgentRepository {
        val database = AgentDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.getApiService())
        val localDataSource = LocalDataSource.getInstance(database.agentDao())
        val appExecutors = AppExecutors()

        return AgentRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideAgentUseCase(context: Context): AgentUseCase {
        val repository = provideRepository(context)
        return AgentInteractor(repository)
    }
}