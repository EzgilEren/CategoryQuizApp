package com.ezgieren.plantidentifyapp.repository

import com.ezgieren.plantidentifyapp.data.local.dao.QuestionDao
import com.ezgieren.plantidentifyapp.data.remote.QuestionApiService
import com.ezgieren.plantidentifyapp.domain.model.Question
import com.ezgieren.plantidentifyapp.domain.repository.QuestionRepository
import com.ezgieren.plantidentifyapp.data.local.entity.QuestionEntity
import com.ezgieren.plantidentifyapp.utils.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class QuestionRepositoryImpl @Inject constructor(
    private val apiService: QuestionApiService,
    private val dao: QuestionDao
) : QuestionRepository {

    override suspend fun getQuestions(): Flow<Resource<List<Question>>> = flow {
        emit(Resource.Loading())

        // firstly cache control
        val cached = dao.getAllQuestions().firstOrNull().orEmpty()
        if (cached.isNotEmpty()) {
            emit(Resource.Success(cached.map { it.toDomain() }))
        }

        try {
            val response = apiService.getQuestions()
            if (response.isSuccessful) {
                val dtoList = response.body().orEmpty()
                val entities = dtoList.map {
                    QuestionEntity(
                        id = it.id,
                        title = it.title ?: Constants.EMPTY,
                        subtitle = it.subtitle ?: Constants.NO_CATEGORY,
                        imageUri = it.imageUri ?: Constants.EMPTY,
                        uri = it.uri ?: Constants.EMPTY,
                        order = it.order ?: 0
                    )
                }

                // Cache update
                dao.clearAll()
                dao.insertAll(entities)

                // Spread new data
                emit(Resource.Success(entities.map { it.toDomain() }))
            } else {
                emit(Resource.Error(Constants.DEFAULT_ERROR_MESSAGE, response.message()))
            }
        } catch (e: Exception) {
            if (cached.isEmpty()) {
                emit(Resource.Error(Constants.NETWORK_ERROR_MESSAGE, e.localizedMessage ?: Constants.NO_DETAILS))
            }
        }
    }
}