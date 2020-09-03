package com.publicis.henripotier.api

import com.publicis.henripotier.model.Book
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Aya Boussaadia on 04,September,2020
 */
interface ApiService {
    @GET("books")
    fun getBooks(): Call<List<Book>>


}