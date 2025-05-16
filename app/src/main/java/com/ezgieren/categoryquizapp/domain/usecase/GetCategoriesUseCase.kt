package com.ezgieren.categoryquizapp.domain.usecase

import com.ezgieren.categoryquizapp.domain.model.Category
import com.ezgieren.categoryquizapp.domain.repository.CategoryRepository
import com.ezgieren.categoryquizapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: CategoryRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<Category>>> = repository.getCategories()
}