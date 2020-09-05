package com.nazar.theforesttest.core.db.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.nazar.theforesttest.core.model.Weight

class WeightTypeConverter {
    @TypeConverter
    fun toWeight(json: String): Weight {
        return Gson().fromJson(json,Weight::class.java)
    }

    @TypeConverter
    fun fromWeight(weight: Weight): String {
        return Gson().toJson(weight)
    }
}