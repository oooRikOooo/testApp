package com.example.testapp.FilmsViewPager

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener

import com.example.testapp.ForApi.*
import com.example.testapp.R
import kotlinx.android.synthetic.main.fragment_films_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FilmsListFragment : Fragment() {

    private val dataList: MutableList<Result> = mutableListOf()
    private lateinit var myAdapter: MyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_films_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myAdapter = MyAdapter(dataList)


        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.addItemDecoration(DividerItemDecoration(activity,OrientationHelper.VERTICAL))
        recyclerView.adapter = myAdapter

        AndroidNetworking.initialize(activity)

        AndroidNetworking.get("https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=a49cf8a5f42225880f049917a2262e81")
            .build().getAsObject(Regres::class.java, object : ParsedRequestListener<Regres>{
                override fun onResponse(response: Regres) {
                    dataList.addAll(response.results)
                    myAdapter.notifyDataSetChanged()
                    Log.e("success", response.results.toString())

                }

                override fun onError(anError: ANError?) {

                }

            })

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



    }

}