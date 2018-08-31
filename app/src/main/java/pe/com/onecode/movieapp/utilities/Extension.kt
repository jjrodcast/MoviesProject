/*
 *
 *  * Created by Jorge Rodríguez on 30/08/18 08:01 PM
 *  * Copyright (c) 2018. All rights reserved.
 *  *
 *  * Last modified 30/08/18 02:05 PM
 *
 */

package pe.com.onecode.movieapp.utilities

import android.app.Activity
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import pe.com.onecode.movieapp.R
import java.util.*

fun ViewGroup.inflateView(@LayoutRes resource: Int): View {
    return LayoutInflater.from(context).inflate(resource, this, false)
}

fun Activity.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun ImageView.load(url: String) {
    GlideApp.with(context)
            .load(url)
            .placeholder(R.drawable.image_placeholder)
            .into(this)
}