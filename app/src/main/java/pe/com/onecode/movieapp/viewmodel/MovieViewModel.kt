/*
 *
 *  * Created by Jorge Rodríguez on 30/08/18 08:04 PM
 *  * Copyright (c) 2018. All rights reserved.
 *  *
 *  * Last modified 30/08/18 07:49 PM
 *
 */

package pe.com.onecode.movieapp.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import android.arch.paging.RxPagedListBuilder
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import pe.com.onecode.movieapp.model.entities.Movie
import pe.com.onecode.movieapp.model.io.repository.MovieRepository
import pe.com.onecode.movieapp.model.io.paging.MovieDataSourceFactory

class MovieViewModel(repository: MovieRepository) : ViewModel() {

    val movieList: Observable<PagedList<Movie>>
    private val disposable = CompositeDisposable()
    private val sourceFactory: MovieDataSourceFactory

    init {
        sourceFactory = MovieDataSourceFactory(repository, disposable)

        val config = PagedList.Config.Builder()
                .setPageSize(MovieRepository.ITEM_PER_PAGE)
                .setInitialLoadSizeHint(MovieRepository.ITEM_PER_PAGE * 2)
                .setEnablePlaceholders(false)
                .setPrefetchDistance(MovieRepository.ITEM_PER_PAGE)
                .build()

        movieList = RxPagedListBuilder(sourceFactory, config)
                .setFetchScheduler(Schedulers.io())
                .buildObservable()
                .observeOn(AndroidSchedulers.mainThread())
    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}