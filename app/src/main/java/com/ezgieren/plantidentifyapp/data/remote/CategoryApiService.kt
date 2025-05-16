package com.ezgieren.plantidentifyapp.data.remote

import com.ezgieren.plantidentifyapp.data.model.category.CategoryResponse
import com.ezgieren.plantidentifyapp.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

interface CategoryApiService {

    @GET(Constants.ENDPOINT_GET_CATEGORIES)
    suspend fun getCategories(): Response<CategoryResponse>
}