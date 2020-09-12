package com.publicis.henripotier.Repository

import androidx.lifecycle.LiveData
import com.publicis.henripotier.api.ApiClient
import com.publicis.henripotier.model.Book
import com.publicis.henripotier.storage.BookDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor

/**
 * Created by Aya Boussaadia on 06,September,2020
 */

class BookRepository(private val bookDao: BookDao, private val executor: Executor) {

    fun getBookList(onResult: (isSuccess: Boolean, response: List<Book>?) -> Unit) {

        ApiClient.instance.getListOfBooks().enqueue(object : Callback<List<Book>> {
            override fun onResponse(call: Call<List<Book>>?, response: Response<List<Book>>?) {
                if (response != null && response.isSuccessful) {
                    onResult(true, response.body())
                } else
                    onResult(false, null)
            }

            override fun onFailure(call: Call<List<Book>>?, t: Throwable?) {
                onResult(false, null)
            }

        })
    }


    fun insertOrUpdateBook(book: Book) {
        val number = bookDao.count(book.isbn)
        if (number == null)
            bookDao.updateCart(book.isbn, 1)
        else if (number == 0) {
            book.cartSize = 1
            bookDao.insertBook(book)
        } else
            bookDao.updateCart(book.isbn, number + 1)
    }


}