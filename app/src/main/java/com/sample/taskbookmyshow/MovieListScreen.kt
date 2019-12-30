package com.sample.taskbookmyshow


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.taskbookmyshow.model.MovieData
import com.sample.taskbookmyshow.mvvm.ApiResponse
import com.sample.taskbookmyshow.mvvm.MainViewModel
import com.sample.taskbookmyshow.mvvm.Status
import com.sample.taskbookmyshow.mvvm.ViewModelFactory

import javax.inject.Inject

class MovieListScreen : AppCompatActivity() {


    @Inject
    private var viewModelFactory: ViewModelFactory? = null
    private lateinit var viewModel: MainViewModel

    private lateinit var recycler_view: RecyclerView
    private lateinit  var progressbar: ProgressBar
    private lateinit  var btn_retry: Button
    private lateinit var txt_error: TextView
    private lateinit  var lnr_error: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list_screen)
        (application as MyApplication).component.doInjection(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.apiResponse.observe(this, Observer<ApiResponse> { this.consumeResponse(it) })

        recycler_view = findViewById(R.id.recycler_view)
        lnr_error = findViewById(R.id.lnr_error)
        btn_retry = findViewById(R.id.btn_retry)
        txt_error = findViewById(R.id.txt_error)
        progressbar = findViewById(R.id.progressbar)

        recycler_view.setHasFixedSize(true)
        recycler_view.layoutManager = LinearLayoutManager(this)
        callMoveAPI()
        btn_retry.setOnClickListener { callMoveAPI() }

    }

    internal fun callMoveAPI() {
        viewModel.getMovies()

    }

    private fun consumeResponse(apiResponse: ApiResponse) {

        when (apiResponse.status) {
            Status.LOADING -> {
                progressbar.visibility = View.VISIBLE
                lnr_error.visibility = View.GONE
            }
            Status.SUCCESS -> {
                progressbar.visibility = View.GONE
                lnr_error.visibility = View.GONE
                recycler_view.visibility = View.VISIBLE
                setRecycler_view(apiResponse.data!!)
            }
            Status.ERROR -> {
                progressbar.visibility = View.GONE
                recycler_view.visibility = View.GONE
                lnr_error.visibility = View.VISIBLE
                Log.d("CheckData", "error ")
            }
            Status.FAILURE -> {
                progressbar.visibility = View.GONE
                recycler_view.visibility = View.GONE
                lnr_error.visibility = View.VISIBLE

                Log.d("CheckData", "failur " + apiResponse.error)
            }
        }
    }

    internal fun setRecycler_view(data: Any) {

        val dataList = data as List<MovieData>
        val movieAdapter = MovieAdapter(this, dataList)
        recycler_view.adapter = movieAdapter
    }
}
