package com.bnsantos.view.skeleton.api

import com.bnsantos.view.skeleton.model.Person

data class ApiResponse(
        val mResults: List<Person>?
)