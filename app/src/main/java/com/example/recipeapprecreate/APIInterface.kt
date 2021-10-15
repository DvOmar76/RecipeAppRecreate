package com.example.recipeapprecreate

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIInterface {
    @GET("/recipes/")
    fun getData():Call<Array<DataBookItem>>
    @POST("/recipes/")
    fun addBook(@Body obBook:DataBookItem):Call<DataBookItem>
}