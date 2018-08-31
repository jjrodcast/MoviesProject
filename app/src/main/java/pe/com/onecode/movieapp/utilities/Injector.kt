/*
 *
 *  * Created by Jorge Rodríguez on 30/08/18 08:01 PM
 *  * Copyright (c) 2018. All rights reserved.
 *  *
 *  * Last modified 30/08/18 02:29 AM
 *
 */

package pe.com.onecode.movieapp.utilities

import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import pe.com.onecode.movieapp.model.io.repository.MovieRepository
import pe.com.onecode.movieapp.viewmodel.ViewModelFactory

object Injector {


    fun createViewModelProvider(context: Context): ViewModelProvider.Factory {
        return ViewModelFactory(MovieRepository())
    }

}