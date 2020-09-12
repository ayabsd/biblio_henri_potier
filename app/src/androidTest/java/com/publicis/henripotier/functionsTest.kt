package com.publicis.henripotier

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.publicis.henripotier.Repository.CartRepository
import com.publicis.henripotier.model.Discount
import com.publicis.henripotier.model.DiscountApiResponse
import com.publicis.henripotier.ui.cart.CartViewModel
import com.publicis.henripotier.ui.cart.calculateModule

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class functionsTest {


    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.publicis.henripotier", appContext.packageName)
    }


    @Test
    fun getSliceDiscount() {
        assertEquals(50.0, calculateModule.sliceCalculate(200, 50.0, 250.0), 0.0)
    }

    @Test
    fun getPercent() {
        assertEquals(21.0, calculateModule.percentageCalculate(300.0, 7.0), 0.0)

    }


    @Test
    fun getFinalPrice() {
        val discountOne = Discount("percentage", 5.0, 0)
        val discountTwo = Discount("minus", 4.0, 0)
        val discountThree = Discount("slice", 3.0, 0)
        val discounts = DiscountApiResponse(listOf(discountOne, discountTwo, discountThree))
        assertEquals(171.0, calculateModule.getFinalPrice(discounts, 180.0), 0.0)
    }

}