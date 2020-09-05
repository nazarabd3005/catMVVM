package com.nazar.theforesttest.ui.breeddetail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.nazar.theforesttest.core.model.Breed
import com.nazar.theforesttest.core.model.BreedImage
import com.nazar.theforesttest.core.repo.BreedRepository
import com.nazar.theforesttest.core.utils.Resource
import com.nazar.theforesttest.model.MergeBreed
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.BiFunction


class BreedDetailViewModel @ViewModelInject constructor(
    private val compositeDisposable: CompositeDisposable,
    private val breedRepository: BreedRepository
) : ViewModel() {

    private val breed = MutableLiveData<Resource<Breed>>()

    private val breedImages = MutableLiveData<Resource<ArrayList<BreedImage>>>()

    private val breedImage : LiveData<BreedImage> = Transformations.map(breedImages) {
        it.data?.get(0)
    }

    fun load(id: String, name: String) {
        breed.postValue(Resource.loading(null))
        breedImages.postValue(Resource.loading(null))
        compositeDisposable.add(
            Single.zip(
                breedRepository.getBreedByName(name),
                breedRepository.getBreedImages(id),
                BiFunction { breed, breedImages ->
                    return@BiFunction MergeBreed(breed, breedImages)
                }
            ).subscribe(
                { mergeBreed ->
                    breed.postValue(Resource.success(mergeBreed.breed))
                    breedImages.postValue(Resource.success(mergeBreed.breedImages))
                }, { throwable ->
                    breed.postValue(
                        Resource.error(
                            throwable.message ?: "Something when wrong!",
                            null
                        )
                    )
                    breedImages.postValue(
                        Resource.error(
                            throwable.message ?: "Something when wrong!",
                            null
                        )
                    )
                }
            )
        )

    }

    fun getBreed() : LiveData<Resource<Breed>> {
        return breed
    }

    fun getbreedImages() : LiveData<Resource<ArrayList<BreedImage>>> {
        return breedImages
    }

    fun getBreedImage(): LiveData<BreedImage> {
        return breedImage
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}