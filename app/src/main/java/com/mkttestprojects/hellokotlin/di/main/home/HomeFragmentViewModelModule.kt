package com.mkttestprojects.hellokotlin.di.main.home

import androidx.lifecycle.ViewModel
import com.mkttestprojects.hellokotlin.di.ViewModelKey
import com.mkttestprojects.hellokotlin.ui.main.home.HomeViewModel
import com.mkttestprojects.hellokotlin.ui.main.home.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomeFragmentViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel :: class)
    abstract fun bindHomeViewModel(homeFragmentViewModel: HomeViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel ::class)
    abstract fun bindSearchViewModel(searchViewModel: SearchViewModel) : ViewModel
}