/*
 * Created by Jorge Rodríguez on 26/06/18 12:35 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 23/06/18 11:48 PM
 */
package pe.com.onecode.movieapp.model

import pe.com.onecode.movieapp.entities.ResponseMovies

interface OnMoviesListener {

    fun setUpcomingMovies(movies: ResponseMovies)
}