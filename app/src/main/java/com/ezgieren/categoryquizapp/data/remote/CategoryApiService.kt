package com.ezgieren.categoryquizapp.data.remote

import com.ezgieren.categoryquizapp.data.model.category.CategoryResponse
import retrofit2.Response
import retrofit2.http.GET

interface CategoryApiService {

    @GET("getCategories")
    suspend fun getCategories(): Response<CategoryResponse>
}