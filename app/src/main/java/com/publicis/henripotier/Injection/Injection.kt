package com.publicis.henripotier.Injection

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.publicis.henripotier.Repository.BookRepository
import com.publicis.henripotier.Repository.CartRepository
import com.publicis.henripotier.storage.BooksStrorageDataBase
import com.publicis.henripotier.ui.cart.CartViewModelFactory
import com.publicis.henripotier.ui.listbooks.BookListViewModelFactory
import java.util.concurrent.Executors

/**
 * Created by Aya Boussaadia on 09,September,2020
 */

object Injection {

    fun provideBookRepo(context: Context): BookRepository {
        val database = BooksStrorageDataBase.getInstance(context)
        return BookRepository(database!!.dao(), Executors.newSingleThreadExecutor())
    }

    fun provideViewModelFactoryBookList(context: Context): ViewModelProvider.Factory {
        return BookListViewModelFactory(provideBookRepo(context))
    }


    fun provideViewModelFactoryCart(context: Context): ViewModelProvider.Factory {
        return CartViewModelFactory(provideCartRepo(context))
    }

    fun provideCartRepo(context: Context): CartRepository {
        val database = BooksStrorageDataBase.getInstance(context)
        return CartRepository(database!!.dao(), Executors.newSingleThreadExecutor())
    }


}