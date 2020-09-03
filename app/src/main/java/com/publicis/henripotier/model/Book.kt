package com.publicis.henripotier.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Aya Boussaadia on 04,September,2020
 */
@Entity(tableName = "books")
data class Book(@PrimaryKey val isbn: String,
                @field:SerializedName("title") val title: String,
                @field:SerializedName("price") val price: Int,
                @field:SerializedName("cover") val cover: String,
                @field:SerializedName("synopsis") val synopsis: List<String>,
                @field:SerializedName("nbInBasket") var nbInBasket: Int)



