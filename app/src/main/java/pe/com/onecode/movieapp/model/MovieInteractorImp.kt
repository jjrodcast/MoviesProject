/*
 * Created by Jorge Rodríguez on 26/06/18 12:35 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 23/06/18 11:48 PM
 */
package pe.com.onecode.movieapp.model

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import pe.com.onecode.movieapp.entities.ResponseMovies
import pe.com.onecode.movieapp.io.repository.MovieRepositoryImp

class MovieInteractorImp(val listener: OnMoviesListener) : MovieInteractor {

    private val TAG = MovieRepositoryImp::class.java.canonicalName

    override fun getMovies(page: Int) {

        val r = MovieRepositoryImp().getListOfMovies(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<ResponseMovies>() {
                    override fun onComplete() {
                        Log.d(TAG, "onComplete()")
                    }

                    override fun onNext(movies: ResponseMovies) {
                        listener.setUpcomingMovies(movies)
                    }

                    override fun onError(e: Throwable) {
                        Log.e(TAG, e.message)
                    }

                })

        //r.dispose()

    }
}