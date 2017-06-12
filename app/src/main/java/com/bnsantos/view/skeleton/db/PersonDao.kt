package com.bnsantos.view.skeleton.db

import android.arch.persistence.room.*
import com.bnsantos.view.skeleton.model.Person
import io.reactivex.Flowable

@Dao interface PersonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(people: List<Person>)

    @Query("SELECT * FROM Person")
    fun read(): Flowable<List<Person>>

    @Query("DELETE FROM Person")
    fun clear(): Int
}