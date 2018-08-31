/*
 * Created by Jorge Rodríguez on 26/06/18 12:35 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 23/06/18 11:48 PM
 */

package pe.com.onecode.movieapp.model.io.repository

import io.reactivex.Observable
import pe.com.onecode.movieapp.model.entities.ResponseMovies
import pe.com.onecode.movieapp.model.io.service.MovieDBApi

class MovieRepository {

    fun getListOfMovies(page: Int): Observable<ResponseMovies> {
        return MovieDBApi.retrofit.getListMovies(MovieDBApi.API_KEY, MovieDBApi.LANG, page)

    }

    fun getMovieDetail(movieId: Int): Observable<ResponseMovies> {
        return MovieDBApi.retrofit.getMovieDetail(movieId, MovieDBApi.API_KEY, MovieDBApi.LANG)
    }

    companion object {
        const val ITEM_PER_PAGE = 20
    }

}