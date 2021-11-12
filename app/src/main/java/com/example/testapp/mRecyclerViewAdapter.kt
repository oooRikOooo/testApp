package com.example.testapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.DataBase.Entity

/*
class mRecyclerViewAdapter(private val usersList :List<Entity>):RecyclerView.Adapter<MyviewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item,parent,false)
        return MyviewHolder(binding)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
        holder.bind(usersList[position])

    }

}

class MyviewHolder(private val binding :ListItemBinding ):RecyclerView.ViewHolder(binding.root){

    fun bind(user : Entity){
        binding.edit = user.firstName

    }

}*/
