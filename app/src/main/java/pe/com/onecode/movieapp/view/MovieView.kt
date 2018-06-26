package pe.com.onecode.movieapp.view

import pe.com.onecode.movieapp.entities.ResponseMovies


interface MovieView : BaseView {

    fun showMovies(movies: ResponseMovies)
}