/*
 * Created by Jorge Rodríguez on 26/06/18 12:35 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 23/06/18 11:48 PM
 */

package pe.com.onecode.movieapp.model.entities

import com.google.gson.annotations.SerializedName

data class Movie(@SerializedName("id") val id: Int,
        @SerializedName("title") val title: String,
        @SerializedName("popularity") val popularity: Double,
        @SerializedName("vote_average") val voteAverage: Double,
        @SerializedName("poster_path") val posterPath: String,
        @SerializedName("backdrop_path") val backdropPath: String,
        @SerializedName("release_date") val date: String,
        @SerializedName("adult") val isAdult: Boolean)