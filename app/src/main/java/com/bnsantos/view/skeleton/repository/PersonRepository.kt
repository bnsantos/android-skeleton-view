package com.bnsantos.view.skeleton.repository

import android.util.Log
import com.bnsantos.view.skeleton.api.PeopleService
import com.bnsantos.view.skeleton.db.PersonDao
import com.bnsantos.view.skeleton.model.Person
import com.bnsantos.view.skeleton.vo.Resource
import io.reactivex.Flowable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PersonRepository @Inject constructor(private val mService: PeopleService, private val mDao: PersonDao){

    private fun readCached(count: Int = 10): Flowable<Resource<List<Person>>> {
        return mDao.read()
                .subscribeOn(Schedulers.io())
                .flatMap {
                    if (it != null) {
                        Log.i(this@PersonRepository.javaClass.simpleName, "onNext->Cached people[${it.size}]: " + it)
                        Flowable.just(Resource.Loading(it))
                    }else {
                        Flowable.empty()
                    }
                }
    }

    private fun readServer(count: Int = 10) {
        mService.read(count)
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                        onNext = {
                            if (it != null && it.mResults != null) {
                                Log.i(this@PersonRepository.javaClass.simpleName, "Caching people[${it.mResults.size}]: " + it.mResults)
                                mDao.insert(it.mResults)
                            }else {
                                Log.i(this@PersonRepository.javaClass.simpleName, "No result")
                            }
                        },
                        onError = {
                            Log.e(this@PersonRepository.javaClass.simpleName, "Error", it)
                        },
                        onComplete = {
                            Log.i(this@PersonRepository.javaClass.simpleName, "onCompleted")
                        }
                )
    }

    fun read(): Flowable<Resource<List<Person>>> {
        readServer()
        return readCached()
    }

    fun reload() {
        readServer()
    }
}