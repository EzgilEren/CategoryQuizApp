package com.ezgieren.plantidentifyapp.domain.usecase

import com.ezgieren.plantidentifyapp.domain.model.Question
import com.ezgieren.plantidentifyapp.domain.repository.QuestionRepository
import com.ezgieren.plantidentifyapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetQuestionsUseCase @Inject constructor(
    private val repository: QuestionRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<Question>>> {
        return repository.getQuestions()
    }
}