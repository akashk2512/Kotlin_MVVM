package com.sample.taskbookmyshow.mvvm


import com.sample.taskbookmyshow.model.MovieData
import io.reactivex.Observable

/**
 * Created by AKASH on 29/12/19.
 */
class Repository(private val apiCallInterface: ApiCallInterface) {

    internal val movies: Observable<List<MovieData>>
        get() = apiCallInterface.getMovies()
}
