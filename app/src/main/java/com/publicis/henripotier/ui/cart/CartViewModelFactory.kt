package com.publicis.henripotier.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.publicis.henripotier.Repository.BookRepository
import com.publicis.henripotier.Repository.CartRepository

/**
 * Created by Aya Boussaadia on 10,September,2020
 */

class CartViewModelFactory(private val cartRepository: CartRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            @Suppress("FAILED")
            return CartViewModel(cartRepository) as T
        }
        throw IllegalArgumentException("FEILED")
    }
}