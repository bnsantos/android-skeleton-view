package com.bnsantos.view.skeleton.di.modules

import com.bnsantos.view.skeleton.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module abstract class ActivitiesModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}