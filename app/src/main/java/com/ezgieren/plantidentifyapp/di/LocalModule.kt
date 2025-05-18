package com.ezgieren.plantidentifyapp.di

import android.content.Context
import androidx.room.Room
import com.ezgieren.plantidentifyapp.data.local.AppDatabase
import com.ezgieren.plantidentifyapp.data.local.dao.CategoryDao
import com.ezgieren.plantidentifyapp.data.local.dao.QuestionDao
import com.ezgieren.plantidentifyapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            Constants.PLANT_DATABASE
        ).build()
    }

    @Provides
    fun provideCategoryDao(db: AppDatabase): CategoryDao = db.categoryDao()

    @Provides
    fun provideQuestionDao(db: AppDatabase): QuestionDao = db.questionDao()
}
