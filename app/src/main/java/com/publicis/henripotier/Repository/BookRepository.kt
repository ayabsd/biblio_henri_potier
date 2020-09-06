package com.publicis.henripotier.Repository

import com.publicis.henripotier.api.ApiClient
import com.publicis.henripotier.model.Book
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Aya Boussaadia on 06,September,2020
 */
class BookRepository {

    companion object {
        private var INSTANCE: BookRepository? = null
        fun getInstance() = INSTANCE
            ?: BookRepository().also {
                INSTANCE = it
            }
    }

    // GET book list
    fun getBookList(onResult: (isSuccess: Boolean, response: List<Book>?) -> Unit) {

        ApiClient.instance.getListOfBooks().enqueue(object : Callback<List<Book>> {
            override fun onResponse(call: Call<List<Book>>?, response: Response<List<Book>>?) {
                if (response != null && response.isSuccessful)
                    onResult(true, response.body())
                else
                    onResult(false, null)
            }

            override fun onFailure(call: Call<List<Book>>?, t: Throwable?) {
                onResult(false, null)
            }

        })
    }


}