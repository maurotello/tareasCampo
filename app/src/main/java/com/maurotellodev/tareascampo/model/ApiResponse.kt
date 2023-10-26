package com.maurotellodev.tareascampo.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    val data: List<JobDataItem> = emptyList()
)

sealed class JobDataItem

data class JobItem(
    @SerializedName("type") val type: Int,
    @SerializedName("name") val name: String,
    @SerializedName("tamanio") val tamanio: Int,
    @SerializedName("show") val show: String,
    @SerializedName("editable") val editable: Boolean,
    @SerializedName("options") val options: String
) : JobDataItem()

data class Results(
    val result: JobsModel
)

data class JobDataString(
    val dataString: String
) : JobDataItem()