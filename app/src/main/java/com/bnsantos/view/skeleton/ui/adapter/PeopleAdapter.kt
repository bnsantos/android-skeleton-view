package com.bnsantos.view.skeleton.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bnsantos.view.skeleton.R
import com.bnsantos.view.skeleton.model.Person
import com.facebook.drawee.view.SimpleDraweeView

class PeopleAdapter(people: List<Person> = listOf()) : SkeletonAdapter<Person, PeopleAdapter.PersonHolder>(people) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PersonHolder {
        return PersonHolder(LayoutInflater.from(parent?.context).inflate(R.layout.adapter_person, parent, false))
    }

    override fun onBind(holder: PersonHolder, pos: Int) {
        val person = mItems[pos]
        holder.mName.text = person.name
        holder.mEmail.text = person.email
        holder.mPicture.setImageURI(person.picture)
    }

    override fun setHolderSkeleton(holder: PersonHolder) {
        holder.mName.text = ""
        holder.mEmail.text = ""
        holder.mPicture.setActualImageResource(R.drawable.round)
        holder.mEmail.setBackgroundResource(R.color.lightGray)
        holder.mName.setBackgroundResource(R.color.lightGray)
    }

    override fun clearHolderSkeleton(holder: PersonHolder) {
        holder.mEmail.setBackgroundResource(0)
        holder.mName.setBackgroundResource(0)
    }

    fun swap(people: List<Person>){
        setSkeleton(false)
        mItems = people
        notifyDataSetChanged()
    }

    class PersonHolder(view: View): RecyclerView.ViewHolder(view) {
        val mName : TextView = view.findViewById(R.id.name) as TextView
        val mEmail : TextView = view.findViewById(R.id.email) as TextView

        val mPicture : SimpleDraweeView = view.findViewById(R.id.picture) as SimpleDraweeView
    }
}