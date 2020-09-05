package com.nazar.theforesttest.core.repo

import com.nazar.theforesttest.core.api.BreedService
import com.nazar.theforesttest.core.db.dao.BreedDao
import com.nazar.theforesttest.core.model.Breed
import com.nazar.theforesttest.core.model.BreedImage
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class BreedRepository @Inject constructor(
    val breedDao: BreedDao,
    private val breedService: BreedService
) {

    fun getBreeds() : Single<ArrayList<Breed>> {
        return breedService.getBreeds().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getBreedByName(name: String) : Single<Breed> {
        return breedService.getBreedSearchByName(name).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it[0] } // get first item
    }

    fun getBreedImages(id: String): Single<ArrayList<BreedImage>> {
        return breedService.getBreedImage(id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}