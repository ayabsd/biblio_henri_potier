package com.publicis.henripotier.ui.cart

import androidx.lifecycle.LiveData
import com.publicis.henripotier.Repository.BookRepository
import com.publicis.henripotier.Repository.CartRepository
import com.publicis.henripotier.model.Book
import com.publicis.henripotier.ui.BaseViewModel

/**
 * Created by Aya Boussaadia on 10,September,2020
 */

class CartViewModel(private val bookRepository: CartRepository) : BaseViewModel() {


    var bookList: LiveData<List<Book>> = bookRepository.getAllBasketBook()

    fun addBook(book: Book?) {
        if (book != null ) {
            bookRepository.insertOrUpdateBook(book)
            toastMessage.value = book.title + " successfully added"
        }
    }

    fun removeBook(book: Book?) {
        if (book != null ) {
            bookRepository.deleteBook(book)
            toastMessage.value = book.title + " successfully deleted"
        }
    }


}
