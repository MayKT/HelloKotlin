package com.mkttestprojects.hellokotlin.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mkttestprojects.hellokotlin.R
import com.mkttestprojects.hellokotlin.common.BaseAdapter
import com.mkttestprojects.hellokotlin.models.MovieListInfo
import com.mkttestprojects.hellokotlin.ui.moviedetail.MovieDetailActivity
import com.mkttestprojects.hellokotlin.util.AppConstants.BASE_IMG_URL
import kotlinx.android.synthetic.main.horizonal_movie_item.view.*

class MovieAdapter : BaseAdapter(){

    override fun onCreateCustomHeaderViewHolder(
        parent: ViewGroup?,
        viewType: Int
    ): RecyclerView.ViewHolder? {
        return null
    }

    override fun onBindCustomHeaderViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
    }

    override fun onCreateCustomViewHolder(
        parent: ViewGroup?,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.horizonal_movie_item,parent,false)
        return ViewHolder(view)    }

    override fun onBindCustomViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as ViewHolder).bind(itemsList[position] as MovieListInfo)
    }

    class ViewHolder ( itemView : View) : RecyclerView.ViewHolder(itemView) {

        val context : Context = itemView.context

        fun bind( movieListInfo: MovieListInfo){

            Glide.with(context).load(BASE_IMG_URL + movieListInfo.poster_path).into(itemView.image_view_movie)

            itemView.setOnClickListener {
                context.startActivity(MovieDetailActivity.MovieDetailActivityIntent(context,movieListInfo.id))
            }
        }
    }

}