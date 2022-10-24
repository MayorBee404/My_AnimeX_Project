package com.dicoding.core.di

import androidx.room.Room
import com.dicoding.core.BuildConfig.DEBUG
import com.dicoding.core.data.source.local.room.AnimeDatabase
import com.dicoding.core.data.source.remote.RemoteDataSource
import com.dicoding.core.data.source.remote.network.ApiService
import com.dicoding.core.domain.repository.IAnimeRepository
import com.dicoding.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<AnimeDatabase>().animeDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("animex".toCharArray())
        val factory = SupportFactory(passphrase)

        Room.databaseBuilder(
            androidContext(),
            AnimeDatabase::class.java, "Anime.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val loggingInterceptor =
            if (DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(loggingInterceptor))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()

    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://kitsu.io/api/edge/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { com.dicoding.core.data.source.local.LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IAnimeRepository> {
        com.dicoding.core.data.AnimeRepository(
            get(),
            get(),
            get()
        )
    }
}