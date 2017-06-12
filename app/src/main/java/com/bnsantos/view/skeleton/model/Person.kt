package com.bnsantos.view.skeleton.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Person(
        @PrimaryKey(autoGenerate = true)val id: Long,
        val name: String,
        val email: String,
        val gender: String,
        val picture: String
)