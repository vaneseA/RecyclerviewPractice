package com.example.recyclerviewpractice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewpractice.databinding.ItemRecyclerBinding

class MyAdapter : RecyclerView.Adapter<Holder>(){

    var listData = mutableListOf<Member>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val member = listData[position]
        holder.setData(member, position)
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}

class Holder(val binding: ItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root){

    private val mainActivity = MainActivity.getInstance()
    var mMember: Member? = null
    var mPosition: Int? = null

    init {
        binding.btnDelete.setOnClickListener {
            mainActivity?.deleteMember(mMember!!)
        }

        binding.btnEdit.setOnClickListener {
            mainActivity?.editMember(mPosition!!, mMember!!)
        }
    }

    fun setData(member: Member, position: Int){
        binding.textView.text = member.name
        this.mMember = member
        this.mPosition = position
    }
}