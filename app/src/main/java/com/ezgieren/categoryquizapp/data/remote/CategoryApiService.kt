package com.ezgieren.categoryquizapp.data.remote

import com.ezgieren.categoryquizapp.data.model.CategoryDto
import com.ezgieren.categoryquizapp.data.model.CategoryResponse
import retrofit2.Response
import retrofit2.http.GET

interface CategoryApiService {

    @GET("getCategories")
    suspend fun getCategories(): Response<CategoryResponse>
}