package com.bnsantos.view.skeleton.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface PeopleService {
    @GET("api/")
    fun read(@Query(value = "results") results: Int = 10) : Observable<ApiResponse>
}