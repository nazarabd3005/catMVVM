package com.nazar.theforesttest.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.components.FragmentComponent
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
@InstallIn(FragmentComponent::class)
object CompositeDisposableModule {

    @Singleton
    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

}