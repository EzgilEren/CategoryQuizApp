package com.ezgieren.plantidentifyapp.di

import com.ezgieren.plantidentifyapp.data.remote.CategoryApiService
import com.ezgieren.plantidentifyapp.data.remote.QuestionApiService
import com.ezgieren.plantidentifyapp.utils.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    // Category API Service
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): CategoryApiService =
        retrofit.create(CategoryApiService::class.java)

    // Question API Service
    @Provides
    @Singleton
    fun provideQuestionApiService(retrofit: Retrofit): QuestionApiService =
        retrofit.create(QuestionApiService::class.java)
}