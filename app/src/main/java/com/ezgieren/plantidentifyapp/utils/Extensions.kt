package com.ezgieren.plantidentifyapp.utils

import com.ezgieren.plantidentifyapp.data.local.entity.CategoryEntity
import com.ezgieren.plantidentifyapp.data.local.entity.QuestionEntity
import com.ezgieren.plantidentifyapp.data.model.category.CategoryDto
import com.ezgieren.plantidentifyapp.data.model.question.QuestionDto
import com.ezgieren.plantidentifyapp.domain.model.Category
import com.ezgieren.plantidentifyapp.domain.model.Question

fun QuestionDto.toDomain(): Question {
    return Question(
        id = id,
        title = title ?: "Untitled",
        subtitle = subtitle ?: Constants.NO_CATEGORY,
        imageUrl = imageUri ?: Constants.EMPTY,
        link = uri ?: Constants.EMPTY,
        order = order ?: 0
    )
}

// CategoryDto to Entity Conversion
fun CategoryDto.toEntity(): CategoryEntity? {
    return this.name?.let {
        CategoryEntity(
            id = this.id,
            name = it,
            imageUri = this.image?.url.orEmpty()
        )
    }
}

// Entity to Domain Model Conversion
fun CategoryEntity.toDomain(): Category {
    return Category(
        id = this.id,
        title = this.name,
        imageUrl = imageUri
    )
}

fun QuestionEntity.toDomain(): Question {
    return Question(
        id = id,
        title = title,
        subtitle = subtitle,
        imageUrl = imageUri,
        link = uri,
        order = order
    )
}