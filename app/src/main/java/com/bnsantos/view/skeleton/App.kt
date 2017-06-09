package com.bnsantos.view.skeleton

import android.app.Activity
import android.app.Application
import com.bnsantos.view.skeleton.di.AppInjector
import com.facebook.drawee.backends.pipeline.Fresco
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector{
    @Inject
    lateinit var mDispatchingAndroidInjector : DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
        Fresco.initialize(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return mDispatchingAndroidInjector
    }
}