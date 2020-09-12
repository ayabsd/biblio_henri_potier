package com.publicis.henripotier.ui.listbooks

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import com.publicis.henripotier.Repository.BookRepository
import com.publicis.henripotier.model.Book
import com.publicis.henripotier.ui.BaseViewModel
import org.jetbrains.anko.doAsync
import java.lang.reflect.Field

/**
 * Created by Aya Boussaadia on 06,September,2020
 */

class BookListViewModel(private val bookRepository: BookRepository) : BaseViewModel() {
    val bookList = MutableLiveData<List<Book>>()

    fun fetchBookList() {
        dataLoading.value = false
        bookRepository.getBookList() { isSuccess, response ->
            dataLoading.value = false
            if (isSuccess) {
                empty.value = false
                bookList.value = response
            } else {
                empty.value = true
            }
        }
    }

    fun addBook(book: Book?) {
        if (book != null ) {
            bookRepository.insertOrUpdateBook(book)
            toastMessage.value = book.title + " successfully added"
        }
    }




}


