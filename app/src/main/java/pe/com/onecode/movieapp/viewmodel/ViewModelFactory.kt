/*
 *
 *  * Created by Jorge Rodríguez on 30/08/18 08:04 PM
 *  * Copyright (c) 2018. All rights reserved.
 *  *
 *  * Last modified 30/08/18 02:29 AM
 *
 */

package pe.com.onecode.movieapp.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import pe.com.onecode.movieapp.model.io.repository.MovieRepository

class ViewModelFactory(private val respository: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieViewModel(respository) as T
        }
        throw IllegalArgumentException("Illegal ViewModel class")
    }
}