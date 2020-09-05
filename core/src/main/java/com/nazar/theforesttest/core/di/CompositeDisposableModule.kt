package com.nazar.theforesttest.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object CompositeDisposableModule {

    @Singleton
    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

}