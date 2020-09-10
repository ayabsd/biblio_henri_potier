package com.publicis.henripotier.storage

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.publicis.henripotier.model.Book

/**
 * Created by Aya Boussaadia on 07,September,2020
 */
@Dao
interface BookDao{

    @Query("SELECT isbn, title, price, cover, synopsis, cartSize FROM Table_Books")
    fun getAllBooks(): LiveData<List<Book>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBook(book: Book)

    @Query("SELECT isbn, title, price, cover, synopsis, cartSize FROM Table_Books WHERE cartSize" +
            " != 0")
    fun getAllCartElement(): LiveData<List<Book>>

    @Query("UPDATE Table_Books SET cartSize = :count WHERE isbn = :isbn")
    fun updateCart(isbn : String, count : Int)

    @Query("SELECT cartSize FROM Table_Books WHERE isbn = :isbn")
    fun count(isbn : String): Int

    @Query("DELETE FROM table_books WHERE isbn = :isbn")
     fun deleteBook(isbn : String);





}
