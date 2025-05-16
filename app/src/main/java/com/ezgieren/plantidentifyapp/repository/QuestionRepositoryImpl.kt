package com.ezgieren.plantidentifyapp.repository

import com.ezgieren.plantidentifyapp.data.remote.QuestionApiService
import com.ezgieren.plantidentifyapp.domain.model.Question
import com.ezgieren.plantidentifyapp.domain.repository.QuestionRepository
import com.ezgieren.plantidentifyapp.utils.Constants
import com.ezgieren.plantidentifyapp.utils.Resource
import com.ezgieren.plantidentifyapp.utils.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class QuestionRepositoryImpl @Inject constructor(
    private val apiService: QuestionApiService
) : QuestionRepository {

    override suspend fun getQuestions(): Flow<Resource<List<Question>>> = flow {
        emit(Resource.Loading())
        try {
            val response = apiService.getQuestions()
            if (response.isSuccessful) {
                val body = response.body().orEmpty() // get list
                val questions = body.map { it.toDomain() }
                emit(Resource.Success(questions))
            } else {
                emit(Resource.Error(Constants.DEFAULT_ERROR_MESSAGE, response.message()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(Constants.NETWORK_ERROR_MESSAGE, e.localizedMessage ?: Constants.NO_DETAILS))
        }
    }
}