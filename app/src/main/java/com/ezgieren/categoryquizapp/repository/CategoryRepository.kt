package com.ezgieren.categoryquizapp.repository

import com.ezgieren.categoryquizapp.data.model.CategoryDto
import com.ezgieren.categoryquizapp.data.model.CategoryResponse
import com.ezgieren.categoryquizapp.data.remote.CategoryApiService
import com.ezgieren.categoryquizapp.utils.Constants
import com.ezgieren.categoryquizapp.utils.Resource
import retrofit2.Response
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val apiService: CategoryApiService
) {

    suspend fun getCategories(): Resource<List<CategoryDto>> {
        return safeApiCall { apiService.getCategories() }
    }

    private suspend fun <T> safeApiCall(apiCall: suspend () -> Response<CategoryResponse>): Resource<T> {
        return try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                if (body?.data != null) {
                    @Suppress("UNCHECKED_CAST")
                    Resource.Success(body.data as T)
                } else {
                    Resource.Error(
                        message = Constants.API_ERROR_MESSAGE,
                        detailedMessage = Constants.NO_DETAILS
                    )
                }
            } else {
                Resource.Error(
                    message = Constants.UNKNOWN_ERROR_MESSAGE,
                    detailedMessage = response.message()
                )
            }
        } catch (e: Exception) {
            Resource.Error(
                message = Constants.NETWORK_ERROR_MESSAGE,
                detailedMessage = e.localizedMessage ?: Constants.NO_DETAILS
            )
        }
    }
}