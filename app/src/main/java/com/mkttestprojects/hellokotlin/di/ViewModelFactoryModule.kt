package com.mkttestprojects.hellokotlin.di

import androidx.lifecycle.ViewModelProvider
import com.mkttestprojects.hellokotlin.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelProviderFactory(viewModelProviderFactory : ViewModelProviderFactory) : ViewModelProvider.Factory

}