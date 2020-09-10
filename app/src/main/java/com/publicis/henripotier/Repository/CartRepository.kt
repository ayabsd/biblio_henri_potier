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
 * Created by Aya Boussaadia on 10,September,2020
 */

class CartRepository(private val bookDao: BookDao, private val executor: Executor) {





    fun getAllBasketBook(): LiveData<List<Book>> {
        return bookDao.getAllBooks()
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

    fun deleteBook(book: Book) {
        val number = bookDao.count(book.isbn)
        if (number == null)
            bookDao.insertBook(book)
        else if (number == 1)
            bookDao.deleteBook(book.isbn)
        else
            bookDao.updateCart(book.isbn, number - 1)
    }


}