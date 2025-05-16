package com.ezgieren.plantidentifyapp.data.model.question

import com.google.gson.annotations.SerializedName

data class QuestionDto(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("subtitle") val subtitle: String? = null,
    @SerializedName("image_uri") val imageUri: String? = null,
    @SerializedName("uri") val uri: String? = null,
    @SerializedName("order") val order: Int? = null
)