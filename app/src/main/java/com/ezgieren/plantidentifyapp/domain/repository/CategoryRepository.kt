package com.ezgieren.plantidentifyapp.domain.repository

import com.ezgieren.plantidentifyapp.domain.model.Category
import com.ezgieren.plantidentifyapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun getCategories(): Flow<Resource<List<Category>>>
}