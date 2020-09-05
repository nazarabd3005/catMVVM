package com.nazar.theforesttest.core.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nazar.theforesttest.core.model.BreedImage

@Dao
interface BreedImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<BreedImage>)

    @Query("SELECT * FROM image_breed")
    fun getAll() : LiveData<List<BreedImage>>
}