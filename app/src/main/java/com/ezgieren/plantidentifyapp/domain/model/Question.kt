package com.ezgieren.plantidentifyapp.domain.model

data class Question(
    val id: Int,
    val title: String,
    val subtitle: String,
    val imageUrl: String,
    val link: String,
    val order: Int
)