package com.bnsantos.view.skeleton.viewmodel

import com.bnsantos.view.skeleton.model.Person
import com.bnsantos.view.skeleton.repository.PersonRepository
import com.bnsantos.view.skeleton.vo.Resource
import io.reactivex.Flowable
import javax.inject.Inject

class PeopleViewModel @Inject constructor(private val mRepo: PersonRepository){
    fun read(): Flowable<Resource<List<Person>>> = mRepo.read()
    fun reload() {
        mRepo.reload()
    }
}