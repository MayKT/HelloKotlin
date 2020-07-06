package com.mkttestprojects.hellokotlin.di.main.home

import com.mkttestprojects.hellokotlin.di.main.MainScope
import com.mkttestprojects.hellokotlin.network.HomeApi
import com.mkttestprojects.hellokotlin.network.SearchApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class HomeFragmentModule {

    @HomeFragmentScope
    @Provides
    fun provideHomeApi(retrofit: Retrofit) : HomeApi {
        return retrofit.create(HomeApi ::class.java)
    }

    @HomeFragmentScope
    @Provides
    fun provideSearchApi(retrofit: Retrofit) : SearchApi{
        return retrofit.create(SearchApi ::class.java)
    }
}