package com.ezgieren.plantidentifyapp.domain.repository

import com.ezgieren.plantidentifyapp.domain.model.Question
import com.ezgieren.plantidentifyapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface QuestionRepository {
    suspend fun getQuestions(): Flow<Resource<List<Question>>>
}