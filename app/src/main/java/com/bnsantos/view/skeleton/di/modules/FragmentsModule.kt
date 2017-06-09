package com.bnsantos.view.skeleton.di.modules

import com.bnsantos.view.skeleton.ui.PeopleFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module abstract class FragmentsModule {
    @ContributesAndroidInjector
    abstract fun contributePeopleFragment(): PeopleFragment
}