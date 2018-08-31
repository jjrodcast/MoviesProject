/*
 *
 *  * Created by Jorge Rodríguez on 30/08/18 08:02 PM
 *  * Copyright (c) 2018. All rights reserved.
 *  *
 *  * Last modified 30/08/18 07:49 PM
 *
 */

package pe.com.onecode.movieapp.view.ui

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.res.Configuration
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.Toolbar
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import pe.com.onecode.movieapp.R
import pe.com.onecode.movieapp.model.entities.Movie
import pe.com.onecode.movieapp.utilities.Injector
import pe.com.onecode.movieapp.view.ui.adapter.MovieAdapter
import pe.com.onecode.movieapp.view.ui.decorator.CustomDecorator
import pe.com.onecode.movieapp.utilities.toast
import pe.com.onecode.movieapp.view.BaseView
import pe.com.onecode.movieapp.viewmodel.MovieViewModel

class MainActivity : AppCompatActivity(), BaseView {

    private lateinit var adapter: MovieAdapter
    private lateinit var layoutManager: GridLayoutManager
    private lateinit var movieViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        fabTop.setOnClickListener { recyclermovies.scrollToPosition(0) }
    }

    override fun init() {
        createViewModel()
        setSupportActionBar(toolbar as Toolbar)
        createAdapter()
        subscribePageList()
    }

    override fun getContext(): Context = applicationContext

    // To use this, you have to change your manifest and put orientation
    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) layoutManager.spanCount = 3
        else layoutManager.spanCount = 2
    }

    private fun createViewModel() {
        movieViewModel = ViewModelProviders
                .of(this, Injector.createViewModelProvider(applicationContext))
                .get(MovieViewModel::class.java)
    }

    private fun createAdapter() {

        adapter = MovieAdapter(R.layout.item_recycler_movie, this::onMovieClick)

        val orientation = resources.configuration.orientation
        val spanCount = if (orientation == Configuration.ORIENTATION_LANDSCAPE) 3 else 2

        layoutManager = GridLayoutManager(this, spanCount, GridLayoutManager.VERTICAL, false)
        recyclermovies.setHasFixedSize(true)
        recyclermovies.layoutManager = layoutManager

        if (recyclermovies.itemDecorationCount == 0) {
            val decorator = CustomDecorator(this, R.integer.offset)
            recyclermovies.addItemDecoration(decorator)
        }
        recyclermovies.adapter = adapter
    }

    private fun subscribePageList() {
        movieViewModel.movieList.subscribe(
                { pagedList ->
                    adapter.submitList(pagedList)
                },
                { error ->
                    Log.e("subscribePageList()", error.message)
                }
        )
    }

    private fun onMovieClick(movie: Movie) {
        toast(movie.title)
    }
}
