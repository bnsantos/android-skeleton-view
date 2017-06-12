package com.bnsantos.view.skeleton.di.components

import com.bnsantos.view.skeleton.App
import com.bnsantos.view.skeleton.di.modules.ActivitiesModule
import com.bnsantos.view.skeleton.di.modules.AppModule
import com.bnsantos.view.skeleton.di.modules.FragmentsModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        ActivitiesModule::class,
        FragmentsModule::class,
        AppModule::class
))
interface AppComponent {
    @Component.Builder interface Builder {
        @BindsInstance fun application(app: App): Builder
        fun setAppModule(module: AppModule): Builder
        fun build(): AppComponent
    }

    fun inject(app: App)
}