package com.bnsantos.view.skeleton.repository

import com.bnsantos.view.skeleton.api.PeopleService
import com.bnsantos.view.skeleton.model.Person
import com.bnsantos.view.skeleton.vo.Resource
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PersonRepository @Inject constructor(private val mService: PeopleService){

    fun read(count: Int = 10): Observable<Resource<List<Person>>> {
        return mService.read(count)
                .subscribeOn(Schedulers.io())
                .flatMap{
                    if (it.mResults != null) {
                        Observable.just(Resource.Success(it.mResults))
                    }else {
                        Observable.empty()
                    }
                }
    }
}