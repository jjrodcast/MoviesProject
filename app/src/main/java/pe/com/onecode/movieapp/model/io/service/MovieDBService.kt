/*
 * Created by Jorge Rodríguez on 26/06/18 12:35 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 23/06/18 11:48 PM
 */

package pe.com.onecode.movieapp.model.io.service

import io.reactivex.Observable
import pe.com.onecode.movieapp.model.entities.ResponseMovies
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDBService {
    //https://api.themoviedb.org/3/discover/movie?api_key=595a5ae969931649fc8e0d0ac37ec387&page=1&language=es
    //https://api.themoviedb.org/3/movie/upcoming?api_key=595a5ae969931649fc8e0d0ac37ec387&language=es-ES&page=1
        @GET(value = "discover/movie")
    fun getListMovies(@Query("api_key") api_key: String,
                      @Query("language") lang: String,
                      @Query("page") page: Int): Observable<ResponseMovies>


    //https://api.themoviedb.org/3/movie/284053?api_key=595a5ae969931649fc8e0d0ac37ec387&language=es
    @GET(value = "/movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id") movieId: Int,
                       @Query("api_key") api_key: String,
                       @Query("language") lang: String): Observable<ResponseMovies>

    // Buscar el tipo de Pelicula
    //https://api.themoviedb.org/3/movie/284053?api_key=595a5ae969931649fc8e0d0ac37ec387&language=es
}