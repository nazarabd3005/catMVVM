package com.nazar.theforesttest.ui.breedlist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.nazar.theforesttest.core.model.Breed
import com.nazar.theforesttest.core.repo.BreedRepository
import com.nazar.theforesttest.core.utils.Resource
import io.reactivex.rxjava3.disposables.CompositeDisposable

class BreedDropdownListViewModel @ViewModelInject constructor(
    private val compositeDisposable : CompositeDisposable,
    private val breedRepository: BreedRepository
): ViewModel() {
    val selectedName = MutableLiveData<String>("")
    private val breed = MediatorLiveData<Breed>().apply {
        addSource(selectedName) { selectedName ->
            if (selectedName == ""){
                value = null
                return@addSource
            }
            value = breeds.value?.data?.find { it.name == selectedName }
        }
    }

    private val breeds = MutableLiveData<Resource<ArrayList<Breed>>>()
    private val breedDropDownList: LiveData<ArrayList<String>> = Transformations.map(breeds) {
        it.data?.listName()
    }

    init {
        fetchBreeds()
    }

    private fun fetchBreeds(){
        breeds.postValue(Resource.loading(null))
        compositeDisposable.add(
            breedRepository.getBreeds()
                .subscribe(
                    { breedList ->
                        breeds.postValue(Resource.success(breedList))

                    },{throwable ->
                        breeds.postValue(Resource.error(throwable.message?:"Something when wrong!",null))
                    }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getBreeds() : LiveData<Resource<ArrayList<Breed>>>{
        return breeds
    }

    fun getListName() : LiveData<ArrayList<String>> {
        return breedDropDownList
    }

    fun getBreed(): LiveData<Breed> {
        return breed
    }

    fun clear(){
        selectedName.postValue("")
    }

}

fun ArrayList<Breed>.listName(): ArrayList<String>{
    val list = ArrayList<String>()
    for (breed in this){
        list.add(breed.name)
    }

    return list
}
