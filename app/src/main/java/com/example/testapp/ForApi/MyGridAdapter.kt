package com.example.testapp.ForApi

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.FilmsDetailsFragment
import com.example.testapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.grid_layout.view.*
import kotlinx.android.synthetic.main.row_layout.view.*

class MyGridAdapter(private val dataList:MutableList<Result>) : RecyclerView.Adapter<MyHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        context = parent.context
        return MyHolder(LayoutInflater.from(context).inflate(R.layout.grid_layout,parent,false))
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val data = dataList[position]

        val titleTextView = holder.itemView.textViewTitleGrid
        titleTextView.text = data.title

        val iV = holder.itemView.imageViewGrid
        Picasso.get().load("https://image.tmdb.org/t/p/w500${data.poster_path}").into(iV)

        holder.itemView.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("iV", data.poster_path)
            bundle.putString("title", data.title)
            bundle.putString("vote","Оцінка: ${data.vote_average}")
            bundle.putString("vote_count","Всього голосів: ${data.vote_count}")
            bundle.putString("rDate","Дата виходу: ${data.release_date}")
            bundle.putString("language","Мова: ${data.original_language}")
            bundle.putString("overview",data.overview)

            val fragment = FilmsDetailsFragment()
            fragment.arguments = bundle
            val fragmentManager = (holder.itemView.context as FragmentActivity).supportFragmentManager

            fragmentManager.beginTransaction().replace(R.id.fragment,fragment).addToBackStack( "tag" ).commit()
        }


    }

    override fun getItemCount(): Int =  dataList.size
}