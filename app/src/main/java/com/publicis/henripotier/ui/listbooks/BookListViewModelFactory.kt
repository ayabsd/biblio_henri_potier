package com.publicis.henripotier.ui.listbooks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.publicis.henripotier.Repository.BookRepository

/**
 * Created by Aya Boussaadia on 09,September,2020
 */


class BookListViewModelFactory(private val bookRepository: BookRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookListViewModel::class.java)) {
            @Suppress("FAILED")
            return BookListViewModel(bookRepository) as T
        }
        throw IllegalArgumentException("FEILED")
    }
}