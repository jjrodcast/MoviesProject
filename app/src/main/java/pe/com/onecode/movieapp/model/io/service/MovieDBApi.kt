/*
 * Created by Jorge Rodríguez on 26/06/18 12:35 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 23/06/18 11:48 PM
 */

package pe.com.onecode.movieapp.model.io.service

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MovieDBApi {

    companion object {
        val BASE_URL: String = "https://api.themoviedb.org/3/"
        val IMAGE_URL: String = "http://image.tmdb.org/t/p/w300/"
        var API_KEY: String = "595a5ae969931649fc8e0d0ac37ec387"
        var LANG: String = "es"
        val retrofit by lazy {
            Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(MovieDBService::class.java)!!
        }
    }
}