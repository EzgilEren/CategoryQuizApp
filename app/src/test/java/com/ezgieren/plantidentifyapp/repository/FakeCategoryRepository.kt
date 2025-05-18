package com.ezgieren.plantidentifyapp.repository

import com.ezgieren.plantidentifyapp.domain.model.Category
import com.ezgieren.plantidentifyapp.domain.repository.CategoryRepository
import com.ezgieren.plantidentifyapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCategoryRepository : CategoryRepository {
    override suspend fun getCategories(): Flow<Resource<List<Category>>> = flow {
        emit(Resource.Loading())
        emit(Resource.Success(
            listOf(
                Category(1, "Bitkiler", "https://fake.com/bitkiler.jpg"),
                Category(2, "Mantarlar", "https://fake.com/mantarlar.jpg")
            )
        ))
    }
}