package com.bnsantos.view.skeleton.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bnsantos.view.skeleton.R
import com.bnsantos.view.skeleton.di.Injectable
import com.bnsantos.view.skeleton.viewmodel.PeopleViewModel
import com.bnsantos.view.skeleton.vo.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_people.*
import javax.inject.Inject

class PeopleFragment : Fragment(), Injectable{
    @Inject lateinit var mViewModel: PeopleViewModel
    lateinit var mAdapter: PeopleAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_people, container, false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        recyclerView.layoutManager = LinearLayoutManager(context)
        mAdapter = PeopleAdapter()
        recyclerView.adapter = mAdapter

        mViewModel.read()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            when (it){
                                is Resource.Success -> {
                                    Log.i(this@PeopleFragment.javaClass.simpleName, "Success: " + it.data.toString())
                                    mAdapter.swap(it.data)
                                }
                                is Resource.Error -> {
                                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                                    Log.e(this@PeopleFragment.javaClass.simpleName, "Error", it.exception)
                                }
                                is Resource.Loading -> Log.i(this@PeopleFragment.javaClass.simpleName, "Loading: " + it.data.toString())
                            }
                        },
                        onError = {
                            Log.e(this@PeopleFragment.javaClass.simpleName, "Error: ", it.cause)
                            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                        },
                        onComplete = {
                            Log.i(this@PeopleFragment.javaClass.simpleName, "Completed")
                        }
                )
    }
}