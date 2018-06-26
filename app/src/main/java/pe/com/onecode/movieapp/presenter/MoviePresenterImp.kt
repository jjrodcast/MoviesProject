package pe.com.onecode.movieapp.presenter

import pe.com.onecode.movieapp.entities.ResponseMovies
import pe.com.onecode.movieapp.model.MovieInteractor
import pe.com.onecode.movieapp.model.MovieInteractorImp
import pe.com.onecode.movieapp.model.OnMoviesListener
import pe.com.onecode.movieapp.view.MovieView

class MoviePresenterImp(val view: MovieView) : MoviePresenter, OnMoviesListener {

    private var movieModel: MovieInteractor = MovieInteractorImp(this)

    override fun getMovies(page: Int) {
        view.let {
            movieModel.getMovies(page)
        }
    }

    override fun setUpcomingMovies(movies: ResponseMovies) {
        view.let {
            view.showMovies(movies)
        }
    }
}