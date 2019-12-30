package com.sample.taskbookmyshow.mvvm

import com.sample.taskbookmyshow.AppConstant
import com.sample.taskbookmyshow.model.MovieData
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by AKASH on 29/12/19.
 */
interface ApiCallInterface {

    @GET(AppConstant.GET_MOVIES_LIST)
    fun getMovies(): Observable<List<MovieData>>
}
