/*
 *
 *  * Created by Jorge Rodríguez on 30/08/18 08:02 PM
 *  * Copyright (c) 2018. All rights reserved.
 *  *
 *  * Last modified 30/08/18 02:32 AM
 *
 */

package pe.com.onecode.movieapp.view

import android.content.Context

interface BaseView {

    fun init()

    fun getContext(): Context
}