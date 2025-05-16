package com.ezgieren.categoryquizapp.data.model.category

import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("data") val data: List<CategoryDto>
)