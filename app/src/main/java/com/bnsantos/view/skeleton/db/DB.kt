package com.bnsantos.view.skeleton.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.bnsantos.view.skeleton.model.Person

@Database(entities = arrayOf(Person::class), version = 1)
abstract class DB: RoomDatabase() {
    abstract public fun personDao(): PersonDao
}