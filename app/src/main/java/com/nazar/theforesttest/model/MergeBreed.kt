package com.nazar.theforesttest.model

import com.nazar.theforesttest.core.model.Breed
import com.nazar.theforesttest.core.model.BreedImage

data class MergeBreed(
    val breed: Breed,
    val breedImages: ArrayList<BreedImage>
)