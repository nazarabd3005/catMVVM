package com.nazar.theforesttest.core.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nazar.theforesttest.core.model.Breed

@Dao
interface BreedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(breeds: List<Breed>)

    @Query("SELECT * FROM breed")
    fun getBreeds() : LiveData<List<Breed>>
}