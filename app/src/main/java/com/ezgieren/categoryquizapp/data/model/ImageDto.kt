package com.ezgieren.categoryquizapp.data.model

import com.google.gson.annotations.SerializedName

data class ImageDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("width") val width: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("url") val url: String,
    @SerializedName("ext") val ext: String,
    @SerializedName("mime") val mime: String,
    @SerializedName("size") val size: Double,
    @SerializedName("alternativeText") val alternativeText: String?,
    @SerializedName("caption") val caption: String?,
    @SerializedName("formats") val formats: Any?, // unknown
    @SerializedName("hash") val hash: String?,
    @SerializedName("previewUrl") val previewUrl: String?,
    @SerializedName("provider") val provider: String?,
    @SerializedName("provider_metadata") val providerMetadata: Any?, // unknown
    @SerializedName("createdAt") val createdAt: String?,
    @SerializedName("updatedAt") val updatedAt: String?
)