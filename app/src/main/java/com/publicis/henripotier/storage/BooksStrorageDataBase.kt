package com.publicis.henripotier.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.publicis.henripotier.model.Book
import com.publicis.henripotier.utils.Constants

/**
 * Created by Aya Boussaadia on 07,September,2020
 */

@Database(
    entities = [Book::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(com.publicis.henripotier.storage.Converters::class)
abstract class BooksStrorageDataBase : RoomDatabase() {

    abstract fun dao(): BookDao

    companion object {

        private var INSTANCE: BooksStrorageDataBase? = null
        fun getInstance(context: Context): BooksStrorageDataBase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                BooksStrorageDataBase::class.java, Constants.DB_NAME
            ).allowMainThreadQueries()
                .build()
    }
}