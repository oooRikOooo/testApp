package com.example.testapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_films_details.*
import kotlinx.android.synthetic.main.toolbar_films_details.*
import kotlinx.android.synthetic.main.toolbar_profile.*

class FilmsDetailsFragment : Fragment() {

    var navc : NavController?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_films_details, container, false)



        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val args = this.arguments

        if(args!=null) {
            val iV: String? = args?.getString("iV")
            val title: String? = args?.getString("title")
            val vote: String? = args?.getString("vote")
            val vote_count: String? = args?.getString("vote_count")
            val rDate: String? = args?.getString("rDate")
            val language: String? = args?.getString("language")
            val overview: String? = args?.getString("overview")

            Picasso.get().load("https://image.tmdb.org/t/p/w500${iV}").into(imageViewFilmDetails)
            textViewTitleFilmDetails?.text = title.toString()
            textViewVoteFilmDetails?.text = vote
            textViewVoteCountFilmsDetails?.text = vote_count
            textViewReleaseDataFilmDetails?.text = rDate
            textViewLanguageFilmDetails?.text = language
            textViewOverviewFilmDetails?.text = overview
            //Toast.makeText(activity, "Oops sorry..!!${title}", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(activity, "Oops sorry..!!${arguments.toString()}", Toast.LENGTH_SHORT).show();
        }



    }


}