package com.ezgieren.plantidentifyapp.utils

import com.ezgieren.plantidentifyapp.data.model.question.QuestionDto
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