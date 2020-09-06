package com.nazar.theforesttest.core.di.module

import com.nazar.theforesttest.core.api.BreedService
import com.nazar.theforesttest.core.db.CustomDatabase
import com.nazar.theforesttest.core.db.dao.BreedDao
import com.nazar.theforesttest.core.repo.BreedRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.components.FragmentComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(FragmentComponent::class)
object BreedModule {

    @Singleton
    @Provides
    fun provideBreedService(retrofit: Retrofit): BreedService =
        retrofit.create(BreedService::class.java)

    @Singleton
    @Provides
    fun provideBreedDao(customDatabase: CustomDatabase): BreedDao = customDatabase.getBreeds

    @Singleton
    @Provides
    fun provideBreedRepository(breedService: BreedService, breedDao: BreedDao): BreedRepository =
        BreedRepository(
            breedDao, breedService
        )

}