package com.example.dazn.data.api.model

import com.google.gson.annotations.SerializedName

data class ScheduleDto(

    @SerializedName("id") val id: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("subtitle") val subtitle: String?,
    @SerializedName("date") val date: String?,
    @SerializedName("imageUrl") val imageUrl: String?

)