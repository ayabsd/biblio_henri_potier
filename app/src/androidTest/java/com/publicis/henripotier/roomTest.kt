package com.publicis.henripotier

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.publicis.henripotier.model.Book
import com.publicis.henripotier.storage.BookDao
import com.publicis.henripotier.storage.BooksStrorageDataBase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * Created by Aya Boussaadia on 12,September,2020
 */
@RunWith(AndroidJUnit4::class)

class roomTest {

    private lateinit var userDao: BookDao
    private lateinit var db: BooksStrorageDataBase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, BooksStrorageDataBase::class.java).build()
        userDao = db.dao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun getAll() {
        userDao.getAllBooks()
    }


    @Test
    @Throws(Exception::class)
    fun insertBook() {
        var synopsis = listOf<String>("a", "b", "c")
        var book = Book("12345678","test title",10,"",synopsis,2)
        userDao.insertBook(book)
    }


    @Test
    @Throws(Exception::class)
    fun getCartSize() {
        print(userDao.count("12345678"))
    }







}
