package com.nazar.theforesttest.core.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.nazar.theforesttest.core.db.CustomDatabase
import com.nazar.theforesttest.core.di.qualifier.ApiKey
import com.nazar.theforesttest.core.di.qualifier.BaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//*
// using hilt for testing only because not recommended for professional project, still alpha versiob
// */
@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = "https://api.thecatapi.com/v1/"


    //generate from https://thecatapi.com/
    @Singleton
    @ApiKey
    @Provides
    fun provideApiKey(): String = "44bdab77-c5f8-41c8-8b1d-41c586b63b16"


    @Singleton
    @Provides
    fun provideHeaderIntercepter(
        @ApiKey apikey: String
    ) : Interceptor = Interceptor {
        val original = it.request()
        val request = original.newBuilder()
            .header("x-api-key",apikey)
            .build()
        return@Interceptor it.proceed(request)
    }

    @Singleton
    @Provides
    fun provideOkHttp(headerInterceptor: Interceptor): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(headerInterceptor).build()

    @Singleton
    @Provides
    fun provideGson(): Gson = Gson()

    @Singleton
    @Provides
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory =
        GsonConverterFactory.create(gson)

    @Singleton
    @Provides
    fun provideCallAdapter(): RxJava3CallAdapterFactory = RxJava3CallAdapterFactory.create()

    @Singleton
    @Provides
    fun provideRetrofit(
        @BaseUrl baseUrl: String,
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
        rxJava3CallAdapterFactory: RxJava3CallAdapterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .addCallAdapterFactory(rxJava3CallAdapterFactory)
        .build()

    //db
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): CustomDatabase =
        Room.databaseBuilder(context, CustomDatabase::class.java, CustomDatabase.DATABASE_NAME)
            .build()
}