package com.mkttestprojects.hellokotlin.di.main

import com.mkttestprojects.hellokotlin.di.main.home.HomeFragmentModule
import com.mkttestprojects.hellokotlin.di.main.home.HomeFragmentScope
import com.mkttestprojects.hellokotlin.di.main.home.HomeFragmentViewModelModule
import com.mkttestprojects.hellokotlin.di.main.profile.ProfileFragmentModule
import com.mkttestprojects.hellokotlin.di.main.profile.ProfileFragmentScope
import com.mkttestprojects.hellokotlin.di.main.profile.ProfileFragmentViewModelModule
import com.mkttestprojects.hellokotlin.ui.main.home.HomeFragment
import com.mkttestprojects.hellokotlin.ui.main.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @HomeFragmentScope
    @ContributesAndroidInjector(modules = [
    HomeFragmentModule ::class,
    HomeFragmentViewModelModule :: class])
    abstract fun contributeHomeFragment() : HomeFragment

    @ProfileFragmentScope
    @ContributesAndroidInjector(modules = [
    ProfileFragmentViewModelModule ::class,
    ProfileFragmentModule ::class])
    abstract fun contributeProfileFragment() : ProfileFragment
}