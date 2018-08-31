/*
 *
 *  * Created by Jorge Rodríguez on 30/08/18 07:59 PM
 *  * Copyright (c) 2018. All rights reserved.
 *  *
 *  * Last modified 30/08/18 07:59 PM
 *
 */

package pe.com.onecode.movieapp.model.io.paging

import android.arch.paging.DataSource
import io.reactivex.disposables.CompositeDisposable
import pe.com.onecode.movieapp.model.entities.Movie
import pe.com.onecode.movieapp.model.io.repository.MovieRepository

class MovieDataSourceFactory(
        private val repository: MovieRepository,
        private val disposable: CompositeDisposable
) : DataSource.Factory<Int, Movie>() {

    override fun create(): DataSource<Int, Movie> {
        return MovieDataSource(repository, disposable)
    }
}