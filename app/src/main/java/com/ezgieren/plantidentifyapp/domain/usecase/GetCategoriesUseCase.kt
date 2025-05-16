package com.ezgieren.plantidentifyapp.domain.usecase

import com.ezgieren.plantidentifyapp.domain.model.Category
import com.ezgieren.plantidentifyapp.domain.repository.CategoryRepository
import com.ezgieren.plantidentifyapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: CategoryRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<Category>>> = repository.getCategories()
}