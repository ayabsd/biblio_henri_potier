package com.publicis.henripotier.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.publicis.henripotier.Repository.CartRepository
import com.publicis.henripotier.model.Book
import com.publicis.henripotier.model.DiscountApiResponse
import com.publicis.henripotier.ui.BaseViewModel
import kotlin.math.roundToLong

/**
 * Created by Aya Boussaadia on 10,September,2020
 */

class CartViewModel(private val bookRepository: CartRepository) : BaseViewModel() {


    var price = MutableLiveData<Double>()
    var priceInitial = MutableLiveData<Double>()

    var bookList: LiveData<List<Book>> = bookRepository.getAllBasketBook()
    var isbnsList: MutableList<String> = ArrayList()


    fun fetchOffers() {

        for (book in bookList.value!!) {
            isbnsList.add(book.isbn)
        }
        bookRepository.getOffers(isbnsList.joinToString()) { isSuccess, response ->
            dataLoading.value = false
            if (isSuccess) {
                empty.value = false
                priceInitial.value = calculateModule.totalPriceCalculate(bookList.value!!).toDouble()
                price.value = calculateModule.getFinalPrice(
                    response,
                    calculateModule.totalPriceCalculate(bookList.value!!)
                )
                isbnsList.clear()

            } else {
                empty.value = true
                price.value = 0.0
                priceInitial.value = 0.0

            }
        }
    }


    fun addBook(book: Book?) {
        if (book != null) {
            bookRepository.insertOrUpdateBook(book)
            toastMessage.value = book.title + " successfully added"

        }
    }

    fun removeBook(book: Book?) {
        if (book != null) {
            bookRepository.deleteBook(book)
            toastMessage.value = book.title + " successfully deleted"

        }
    }


}

object calculateModule {
    val PERCENTAGE = "percentage"
    val MINUS = "minus"
    val SLICE = "slice"

    fun totalPriceCalculate(bookList: List<Book>): Double {
        var total = 0.0
        val mutableIterator = bookList.iterator()
        for (book in mutableIterator) {
            total += (book.price!! * book.cartSize!!)
        }
        return total
    }


    fun percentageCalculate(price: Double, percentValue: Double): Double {
        return price.times(percentValue).div(100)

    }

    fun sliceCalculate(sliceValue: Int, value: Double, price: Double): Double {
        var sliceDiscount = 0.0
        if (sliceValue == 0) {
            sliceDiscount = 0.0
        } else {
            var divResult = price.div(sliceValue).toInt()

            sliceDiscount = divResult.times(value).toDouble()
        }
        return sliceDiscount
    }

    fun getFinalPrice(discount: DiscountApiResponse?, price: Double): Double {
        var discountValue = 0.0
        discount?.offers?.let { list ->
            for (discount in list) {
                with(discount) {
                    discountValue = kotlin.math.max(
                        discountValue,
                        when (type) {
                            PERCENTAGE -> calculateModule.percentageCalculate(price, value)
                            MINUS -> value
                            SLICE -> calculateModule.sliceCalculate(sliceValue, value, price)
                            else -> 0.0
                        }
                    )
                }
            }
        }
        return price - discountValue
    }



}


