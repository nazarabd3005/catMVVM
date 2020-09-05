package com.nazar.theforesttest.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nazar.theforesttest.core.db.dao.BreedDao
import com.nazar.theforesttest.core.db.dao.BreedImageDao
import com.nazar.theforesttest.core.db.typeconverter.WeightTypeConverter
import com.nazar.theforesttest.core.model.Breed
import com.nazar.theforesttest.core.model.BreedImage

@Database(entities = [Breed::class,BreedImage::class],version = 1,exportSchema = false)
@TypeConverters(WeightTypeConverter::class)
abstract class CustomDatabase : RoomDatabase() {

    abstract val getBreeds: BreedDao

    abstract val getBreedImages: BreedImageDao

    companion object {
        const val DATABASE_NAME = "catDB"
    }
}