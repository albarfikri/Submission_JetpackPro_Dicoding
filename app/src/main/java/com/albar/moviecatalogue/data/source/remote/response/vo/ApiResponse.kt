package com.albar.moviecatalogue.data.source.remote.response.vo

class ApiResponse<T>(val status: StatusResponse, val body: T?, val message: String?) {
    companion object {
        fun <T> success(body: T): ApiResponse<T> = ApiResponse(StatusResponse.SUCCESS, body, null)

        fun <T> error(msg: String, body: T): ApiResponse<T> =
            ApiResponse(StatusResponse.ERROR, body, msg)
    }
}