package com.mkttestprojects.hellokotlin.di.main.profile

import androidx.lifecycle.ViewModel
import com.mkttestprojects.hellokotlin.di.ViewModelKey
import com.mkttestprojects.hellokotlin.ui.main.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ProfileFragmentViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel ::class)
    abstract fun bindProfileViewModel(profileViewModel: ProfileViewModel) : ViewModel
}