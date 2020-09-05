package com.nazar.theforesttest.core.api

import com.nazar.theforesttest.core.model.Breed
import com.nazar.theforesttest.core.model.BreedImage
import com.nazar.theforesttest.core.utils.Resource
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface BreedService {

    @GET(ENDPOINT_BREED_LIST)
    fun getBreeds() : Single<ArrayList<Breed>>

    @GET(ENDPOINT_BREED_SEARCH)
    fun getBreedSearchByName(
        @Query("q") name: String
    ): Single<ArrayList<Breed>>

    @GET(Companion.ENDPOINT_BREED_IMAGE)
    fun getBreedImage(
        @Query("breed_id") breedId:String
    ) : Single<ArrayList<BreedImage>>

    companion object {
        private const val ENDPOINT_BREED_LIST = "breeds"
        private const val ENDPOINT_BREED_IMAGE = "images/search"
        private const val ENDPOINT_BREED_SEARCH = "breeds/search"
    }
}