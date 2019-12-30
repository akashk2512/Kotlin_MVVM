package com.sample.taskbookmyshow.mvvm

import io.reactivex.annotations.NonNull
import io.reactivex.annotations.Nullable

import com.sample.taskbookmyshow.mvvm.Status.*

/**
 * Created by AKASH on 29/12/19.
 */
class ApiResponse(
    var status: Status?,
    var data: Any?,
    var error: Throwable?, message: String?, var errorCode: Int
) {

    var message: String? = null

    init {
        this.message = message
    }

    companion object {

        fun loading(): ApiResponse {
            return ApiResponse(LOADING, null, null, null, 0)
        }

        fun success(@Nullable data: Any, message: String, errorCode: Int): ApiResponse {
            return if (errorCode == 200) {
                ApiResponse(SUCCESS, data, null, message, errorCode)
            } else {
                ApiResponse(FAILURE, data, null, message, errorCode)

            }
        }

        fun error(@NonNull error: Throwable): ApiResponse {
            return ApiResponse(ERROR, null, error, null, 0)

        }
    }
}
