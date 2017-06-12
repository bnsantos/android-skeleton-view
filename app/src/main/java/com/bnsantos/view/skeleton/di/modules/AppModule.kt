package com.bnsantos.view.skeleton.di.modules

import android.app.Application
import android.arch.persistence.room.Room
import com.bnsantos.view.skeleton.api.ApiResponse
import com.bnsantos.view.skeleton.api.ApiResponseDeserializer
import com.bnsantos.view.skeleton.api.PeopleService
import com.bnsantos.view.skeleton.db.DB
import com.bnsantos.view.skeleton.db.PersonDao
import com.bnsantos.view.skeleton.model.Person
import com.bnsantos.view.skeleton.model.PersonDeserializer
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module class AppModule(val mApp: Application) {
    @Singleton @Provides fun gson(): Gson = GsonBuilder()
            .registerTypeAdapter(ApiResponse::class.java, ApiResponseDeserializer)
            .registerTypeAdapter(Person::class.java, PersonDeserializer)
            .create()

    @Singleton @Provides fun peopleService(gson: Gson): PeopleService = Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(PeopleService::class.java)

    @Singleton @Provides fun provideDB() : DB = Room.databaseBuilder(mApp, DB::class.java, "skeleton.db").build()

    @Singleton @Provides fun personDao(db: DB) : PersonDao = db.personDao()

}