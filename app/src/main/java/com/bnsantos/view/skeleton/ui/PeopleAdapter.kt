package com.bnsantos.view.skeleton.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bnsantos.view.skeleton.R
import com.bnsantos.view.skeleton.model.Person
import com.facebook.drawee.view.SimpleDraweeView

class PeopleAdapter(var mPeople: List<Person> = listOf()) : RecyclerView.Adapter<PeopleAdapter.PersonHolder>() {
    private var mSkeletonMode: Boolean = false

    override fun getItemCount(): Int {
        if (mSkeletonMode) {
            return 4
        }else {
            return mPeople.size
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PersonHolder {
        return PersonHolder(LayoutInflater.from(parent?.context).inflate(R.layout.adapter_person, parent, false))
    }

    override fun onBindViewHolder(holder: PersonHolder?, pos: Int) {
        when (holder) {
            is PersonHolder -> {
                if(!mSkeletonMode) {
                    val person = mPeople[pos]
                    holder.mName.text = person.name
                    holder.mEmail.text = person.email
                    holder.mPicture.setImageURI(person.picture)
                    holder.mEmail.setBackgroundResource(0)
                    holder.mName.setBackgroundResource(0)
                }else {
                    holder.mName.text = ""
                    holder.mEmail.text = ""
                    holder.mPicture.setActualImageResource(R.drawable.round)
                    holder.mEmail.setBackgroundResource(R.color.lightGray)
                    holder.mName.setBackgroundResource(R.color.lightGray)
                }
            }
        }
    }

    fun swap(people: List<Person>){
        mSkeletonMode = false
        mPeople = people
        notifyDataSetChanged()
    }

    fun skeleton() {
        mSkeletonMode = true
        mPeople = listOf()
        notifyDataSetChanged()
    }

    class PersonHolder(view: View): RecyclerView.ViewHolder(view) {
        val mName : TextView  = view.findViewById(R.id.name) as TextView
        val mEmail : TextView  = view.findViewById(R.id.email) as TextView

        val mPicture : SimpleDraweeView = view.findViewById(R.id.picture) as SimpleDraweeView
    }
}