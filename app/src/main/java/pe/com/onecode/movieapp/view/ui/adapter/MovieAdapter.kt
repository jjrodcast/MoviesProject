/*
 *
 *  * Created by Jorge Rodríguez on 30/08/18 08:03 PM
 *  * Copyright (c) 2018. All rights reserved.
 *  *
 *  * Last modified 30/08/18 04:33 PM
 *
 */

package pe.com.onecode.movieapp.view.ui.adapter

import android.arch.paging.PagedListAdapter
import android.support.annotation.LayoutRes
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_recycler_movie.view.*
import pe.com.onecode.movieapp.model.entities.Movie
import pe.com.onecode.movieapp.model.io.service.MovieDBApi
import pe.com.onecode.movieapp.utilities.inflateView
import pe.com.onecode.movieapp.utilities.load

class MovieAdapter(@LayoutRes private val resource: Int, private val onItemClick: (movie: Movie) -> Unit = {})
    : PagedListAdapter<Movie, MovieAdapter.ViewHolder>(COMPARABLE) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflateView(resource)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        movie?.let { holder.bind(it, onItemClick) }
    }

    companion object {
        val COMPARABLE = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie, onItemClick: (movie: Movie) -> Unit) {
            itemView.image_movie.load(MovieDBApi.IMAGE_URL.plus(movie.posterPath))
            itemView.image_movie.setOnClickListener { onItemClick(movie) }
            itemView.title_movie.text = movie.title
        }
    }
}