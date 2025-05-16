package com.ezgieren.categoryquizapp.repository

import com.ezgieren.categoryquizapp.data.remote.CategoryApiService
import com.ezgieren.categoryquizapp.domain.model.Category
import com.ezgieren.categoryquizapp.domain.repository.CategoryRepository
import com.ezgieren.categoryquizapp.utils.Constants
import com.ezgieren.categoryquizapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val apiService: CategoryApiService
) : CategoryRepository {

    override suspend fun getCategories(): Flow<Resource<List<Category>>> = flow {
        emit(Resource.Loading())
        try {
            val response = apiService.getCategories()
            if (response.isSuccessful) {
                val body = response.body()
                val categories = body?.data?.map {
                    Category(
                        id = it.id,
                        title = it.title ?: "",
                        imageUrl = it.image?.url ?: ""
                    )
                } ?: emptyList()
                emit(Resource.Success(categories))
            } else {
                emit(Resource.Error(Constants.DEFAULT_ERROR_MESSAGE, response.message()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(Constants.NETWORK_ERROR_MESSAGE, e.localizedMessage ?: Constants.NO_DETAILS))
        }
    }
}

