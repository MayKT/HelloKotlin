package com.mkttestprojects.hellokotlin

import com.mkttestprojects.hellokotlin.custom_control.AndroidCommonSetup
import com.mkttestprojects.hellokotlin.di.DaggerAppComponent
import dagger.android.support.DaggerApplication

class BaseApplication : DaggerApplication(){

    override fun onCreate() {
        super.onCreate()
        AndroidCommonSetup.getInstance().init(applicationContext)
    }

    override fun applicationInjector() = DaggerAppComponent.builder().application(this).build()

}