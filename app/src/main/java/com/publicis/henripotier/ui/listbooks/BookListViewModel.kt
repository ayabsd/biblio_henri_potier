package com.publicis.henripotier.ui.listbooks

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import com.publicis.henripotier.Repository.BookRepository
import com.publicis.henripotier.model.Book
import com.publicis.henripotier.ui.BaseViewModel
import java.lang.reflect.Field

/**
 * Created by Aya Boussaadia on 06,September,2020
 */

class BookListViewModel : BaseViewModel() {
    val bookList = MutableLiveData<List<Book>>()


    fun fetchBookList() {
        dataLoading.value = true
        BookRepository.getInstance().getBookList() { isSuccess, response ->
            dataLoading.value = false
            if (isSuccess) {
                bookList.value = response
                empty.value = false
            } else {
                empty.value = true
            }
        }
    }

}


