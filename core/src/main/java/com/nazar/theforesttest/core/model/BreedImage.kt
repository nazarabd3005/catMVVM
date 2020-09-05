package com.nazar.theforesttest.core.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "image_breed")
data class BreedImage constructor(
    @PrimaryKey
    @SerializedName("id")
    val imageId: String,

    @SerializedName("height")
    val heightImage: Int,
    @SerializedName("url")
    val urlImage: String,
    @SerializedName("width")
    val widthImage: Int
) {
}