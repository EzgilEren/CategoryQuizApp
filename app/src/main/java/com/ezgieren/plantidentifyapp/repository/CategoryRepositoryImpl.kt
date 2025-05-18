package com.ezgieren.plantidentifyapp.repository

import com.ezgieren.plantidentifyapp.data.local.dao.CategoryDao
import com.ezgieren.plantidentifyapp.data.remote.CategoryApiService
import com.ezgieren.plantidentifyapp.domain.model.Category
import com.ezgieren.plantidentifyapp.domain.repository.CategoryRepository
import com.ezgieren.plantidentifyapp.utils.Constants
import com.ezgieren.plantidentifyapp.utils.Resource
import com.ezgieren.plantidentifyapp.utils.toDomain
import com.ezgieren.plantidentifyapp.utils.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val apiService: CategoryApiService,
    private val dao: CategoryDao
) : CategoryRepository {

    override suspend fun getCategories(): Flow<Resource<List<Category>>> = flow {
        emit(Resource.Loading())

        val cached = dao.getAllCategories().firstOrNull().orEmpty()
        if (cached.isNotEmpty()) {
            emit(Resource.Success(cached.map { it.toDomain() }))
        }

        try {
            val response = apiService.getCategories()
            if (response.isSuccessful) {
                val dtoList = response.body()?.data.orEmpty()
                val entities = dtoList.mapNotNull { it.toEntity() }

                // Update cache
                dao.clearAll()
                dao.insertAll(entities)

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