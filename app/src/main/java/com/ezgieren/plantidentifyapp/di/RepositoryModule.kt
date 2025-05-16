package com.ezgieren.plantidentifyapp.di

import com.ezgieren.plantidentifyapp.repository.CategoryRepositoryImpl
import com.ezgieren.plantidentifyapp.domain.repository.CategoryRepository
import com.ezgieren.plantidentifyapp.domain.repository.QuestionRepository
import com.ezgieren.plantidentifyapp.repository.QuestionRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindCategoryRepository(
        impl: CategoryRepositoryImpl
    ): CategoryRepository

    @Binds
    @Singleton
    abstract fun bindQuestionRepository(
        impl: QuestionRepositoryImpl
    ): QuestionRepository
}
