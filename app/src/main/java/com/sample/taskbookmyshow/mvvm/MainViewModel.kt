package com.sample.taskbookmyshow.mvvm


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by AKASH on 29/12/19.
 */
class MainViewModel(private val repository: Repository) : ViewModel() {
    private val disposables = CompositeDisposable()
    val apiResponse = MutableLiveData<ApiResponse>()

    // here api call
    fun getMovies() {
        disposables.add(repository.movies
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { d -> apiResponse.setValue(ApiResponse.loading()) }
            .subscribe({ result -> apiResponse.setValue(ApiResponse.success(result, "", 200)) },
                { error -> apiResponse.setValue(ApiResponse.error(error)) })

        )
    }
}
