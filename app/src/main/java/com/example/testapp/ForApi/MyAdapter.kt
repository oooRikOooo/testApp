package com.example.testapp.ForApi

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.FilmsDetailsFragment
import com.example.testapp.FilmsViewPager.FilmsListFragment
import com.example.testapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_layout.view.*


class MyAdapter(private val dataList:MutableList<Result>) : RecyclerView.Adapter<MyHolder>() {

    var navc : NavController?= null
    private lateinit var context:Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        context = parent.context
        return MyHolder(LayoutInflater.from(context).inflate(R.layout.row_layout,parent,false))
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {

        val data = dataList[position]

        val iV = holder.itemView.imageView

        val titleTextView = holder.itemView.textViewTitle
        titleTextView.text = data.title

        val voteTextView = holder.itemView.textViewVote
        voteTextView.text = "Оцінка: ${data.vote_average}"

        val releaseDataTextView = holder.itemView.textViewReleaseData
        releaseDataTextView.text = "Дата виходу: ${data.release_date}"

        val overviewTextView = holder.itemView.textViewOverview
        overviewTextView.text = data.overview

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

    override fun getItemCount(): Int = dataList.size
}