package com.teamnoyes.balancevote.network

import com.squareup.moshi.Moshi
import com.teamnoyes.balancevote.data.api.BVService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

const val BASE_URL = "http://ec2-3-36-94-154.ap-northeast-2.compute.amazonaws.com:8080"

@Module
@InstallIn(SingletonComponent::class)
object BVApiService {
    val moshi = Moshi.Builder().build()

    @Provides
    fun retrofit(): BVService = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .baseUrl(BASE_URL)
        .build()
        .create(BVService::class.java)
}