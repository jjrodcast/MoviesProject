/*
 *
 *  * Created by Jorge Rodríguez on 30/08/18 08:00 PM
 *  * Copyright (c) 2018. All rights reserved.
 *  *
 *  * Last modified 30/08/18 07:59 PM
 *
 */

package pe.com.onecode.movieapp.model.io.paging

import android.arch.paging.PageKeyedDataSource
import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import pe.com.onecode.movieapp.model.entities.Movie
import pe.com.onecode.movieapp.model.io.repository.MovieRepository

class MovieDataSource(
        private val repository: MovieRepository,
        private val disposable: CompositeDisposable
) : PageKeyedDataSource<Int, Movie>() {

    private val lock = Any()
    private var isRequesting: Boolean = false

    override fun loadInitial(params: LoadInitialParams<Int>, initialCallback: LoadInitialCallback<Int, Movie>) {
        createObservable(0, 1, initialCallback, null)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        val page = params.key
        createObservable(page, page + 1, null, callback)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
    }

    private fun createObservable(currentPage: Int,
                                 nextPage: Int,
                                 initialCallback: LoadInitialCallback<Int, Movie>?,
                                 callback: LoadCallback<Int, Movie>?) {

        if (isRequesting) return
        isRequesting = true

        synchronized(lock) {
            disposable.add(
                    repository.getListOfMovies(currentPage + 1)
                            .map { it -> it.movies }
                            .subscribe(
                                    { movies ->
                                        //Log.d("MovieDataSource", movies?.size.toString())
                                        initialCallback?.onResult(movies, null, nextPage)
                                        callback?.onResult(movies, nextPage)
                                    },
                                    { error ->
                                        //Log.e("MovieDataSource", error.message)
                                    },
                                    {
                                        isRequesting = false
                                    })
            )
        }
    }

}
