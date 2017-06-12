package com.bnsantos.view.skeleton.ui.adapter

import android.support.v7.widget.RecyclerView

abstract class SkeletonAdapter<T, H> (protected var mItems: List<T> = listOf(), private val mSkeletonItems: Int = 4) : RecyclerView.Adapter<H>() where H : RecyclerView.ViewHolder{
    private var mSkeletonMode: Boolean = false

    override fun getItemCount(): Int {
        if (mSkeletonMode) {
            return mSkeletonItems
        }else {
            return mItems.size
        }
    }

    override fun onBindViewHolder(holder: H, pos: Int) {
        if(!mSkeletonMode) {
            clearHolderSkeleton(holder)
            onBind(holder, pos)
        }else {
            setHolderSkeleton(holder)
        }
    }

    fun setSkeleton(skeleton: Boolean){
        if (skeleton) {
            mSkeletonMode = true
            mItems = listOf()
            notifyDataSetChanged()
        }else {
            mSkeletonMode = false
        }
    }

    abstract fun onBind(holder: H, pos: Int)
    abstract fun setHolderSkeleton(holder: H)
    abstract fun clearHolderSkeleton(holder: H)
}