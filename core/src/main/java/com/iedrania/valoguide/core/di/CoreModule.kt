package com.iedrania.valoguide.core.di

import androidx.room.Room.databaseBuilder
import com.iedrania.valoguide.core.data.AgentRepository
import com.iedrania.valoguide.core.data.source.local.LocalDataSource
import com.iedrania.valoguide.core.data.source.local.room.AgentDatabase
import com.iedrania.valoguide.core.data.source.remote.RemoteDataSource
import com.iedrania.valoguide.core.data.source.remote.network.ApiService
import com.iedrania.valoguide.core.domain.repository.IAgentRepository
import com.iedrania.valoguide.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<AgentDatabase>().agentDao() }
    val passphrase: ByteArray = SQLiteDatabase.getBytes("iedrania".toCharArray())
    val factory = SupportFactory(passphrase)
    single {
        databaseBuilder(
            androidContext(), AgentDatabase::class.java, "Agent.db"
        ).fallbackToDestructiveMigration().openHelperFactory(factory).build()
    }
}

val networkModule = module {
    single {
        val hostname = "valorant-api.com"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/HOI0EPOgMxJa8z+60yCSF4TJx0jaAN8TiLcdqIlUyiY=")
            .add(hostname, "sha256/81Wf12bcLlFHQAfJluxnzZ6Frg+oJ9PWY/Wrwur8viQ=")
            .add(hostname, "sha256/hxqRlPTu1bMS/0DITB1SSu0vd4u/8l8TjPgfaAp63Gc=").build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS).readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner).build()
    }
    single {
        val retrofit = Retrofit.Builder().baseUrl("https://valorant-api.com/v1/")
            .addConverterFactory(GsonConverterFactory.create()).client(get()).build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IAgentRepository> {
        AgentRepository(
            get(), get(), get()
        )
    }
}