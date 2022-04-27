package com.example.dependencyinjectionsquare.data.source.remote

import com.example.dependencyinjectionsquare.data.model.response.BaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleApi {

    @GET("news")
    fun getNewsByCategory(@Query("category") category : String) : Call<BaseResponse>
}