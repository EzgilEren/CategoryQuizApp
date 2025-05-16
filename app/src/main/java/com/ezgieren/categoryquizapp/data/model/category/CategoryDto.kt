package com.ezgieren.categoryquizapp.data.model.category

import com.google.gson.annotations.SerializedName

data class CategoryDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("rank") val rank: Int? = null,
    @SerializedName("createdAt") val createdAt: String? = null,
    @SerializedName("updatedAt") val updatedAt: String? = null,
    @SerializedName("publishedAt") val publishedAt: String? = null,
    @SerializedName("image") val image: ImageDto? = null
)