package com.example.norway.di

import android.app.Application
import androidx.room.Room
import com.example.norway.db.AuthDao
import com.example.norway.db.AuthDatabase
import com.example.norway.network.Config
import com.example.norway.network.NotesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }

    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Config.url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideNotesApi(retrofit: Retrofit): NotesApi {
        return retrofit.create(NotesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthDatabase(app: Application): AuthDao {
        return Room.databaseBuilder(app, AuthDatabase::class.java, "auth").build().dao
    }
}
