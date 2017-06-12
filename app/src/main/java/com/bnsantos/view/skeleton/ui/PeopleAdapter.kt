package com.bnsantos.view.skeleton.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bnsantos.view.skeleton.R
import com.bnsantos.view.skeleton.model.Person
import com.facebook.drawee.view.SimpleDraweeView

class PeopleAdapter(var mPeople: List<Person> = listOf()) : RecyclerView.Adapter<PeopleAdapter.PeopleHolder>() {
    override fun getItemCount(): Int = mPeople.size

    override fun onCreateViewHolder(parent: ViewGroup?, p1: Int): PeopleHolder {
        return PeopleHolder(LayoutInflater.from(parent?.context).inflate(R.layout.adapter_person, parent, false))
    }

    override fun onBindViewHolder(holder: PeopleHolder?, pos: Int) {
        if (holder != null) {
            val person = mPeople[pos]
            holder.mName.text = person.name
            holder.mEmail.text = person.email
            holder.mPicture.setImageURI(person.picture)
        }
    }

    fun swap(people: List<Person>){
        mPeople = people
        notifyDataSetChanged()
    }

    fun append(data: List<Person>) {
        mPeople += data
        notifyDataSetChanged()
    }

    class PeopleHolder(view: View): RecyclerView.ViewHolder(view) {
        val mName : TextView  = view.findViewById(R.id.name) as TextView
        val mEmail : TextView  = view.findViewById(R.id.email) as TextView
        val mPicture : SimpleDraweeView = view.findViewById(R.id.picture) as SimpleDraweeView

    }
}