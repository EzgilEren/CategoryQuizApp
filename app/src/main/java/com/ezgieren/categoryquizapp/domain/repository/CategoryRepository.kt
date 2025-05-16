package com.ezgieren.categoryquizapp.domain.repository

import com.ezgieren.categoryquizapp.domain.model.Category
import com.ezgieren.categoryquizapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun getCategories(): Flow<Resource<List<Category>>>
}