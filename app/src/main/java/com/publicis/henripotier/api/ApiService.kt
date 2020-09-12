package com.publicis.henripotier.api

import com.publicis.henripotier.model.Book
import com.publicis.henripotier.model.DiscountApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Aya Boussaadia on 04,September,2020
 */
interface ApiService {

    @GET("books")
    fun getListOfBooks(): Call<List<Book>>

    @GET("books/{isbns}/commercialOffers")
    fun getCommercialOffers(@Path("isbns") isbns: String): Call<DiscountApiResponse>


}