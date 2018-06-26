package pe.com.onecode.movieapp.ui.adapter

import android.support.annotation.LayoutRes
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_recycler_movie.view.*
import pe.com.onecode.movieapp.R
import pe.com.onecode.movieapp.entities.Movie
import pe.com.onecode.movieapp.io.service.MovieDBApi
import pe.com.onecode.movieapp.utilities.GlideApp
import pe.com.onecode.movieapp.utilities.ItemClick
import pe.com.onecode.movieapp.utilities.inflateView

class MovieAdapter(@LayoutRes private val resource: Int, private val movies: ArrayList<Movie>, val itemListener: ItemClick<Movie>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private val listMovies = arrayListOf<Movie>()

    init {
        addData(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        //val view = LayoutInflater.from(parent.context).inflate(resource, parent, false)
        val view = parent.inflateView(resource)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listMovies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listMovies[position], itemListener)
    }

    private fun addData(movies: ArrayList<Movie>) {
        listMovies.addAll(movies);
        notifyDataSetChanged();
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie, clickListener: ItemClick<Movie>) {

            GlideApp.with(itemView.context)
                    .load(MovieDBApi.IMAGE_URL.plus(movie.posterPath))
                    .placeholder(R.drawable.image_placeholder)
                    .into(itemView.image_movie)

            itemView.image_movie.setOnClickListener {
                clickListener.onClick(movie)
            }

            itemView.title_movie.text = movie.title
        }
    }
}