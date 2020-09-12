package com.publicis.henripotier.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.publicis.henripotier.databinding.ItemBookBinding
import com.publicis.henripotier.databinding.ItemCartBinding
import com.publicis.henripotier.model.Book
import com.publicis.henripotier.ui.cart.CartViewModel
import com.publicis.henripotier.ui.holder.CartViewHolder

/**
 * Created by Aya Boussaadia on 10,September,2020
 */


class CartAdapter(
    private val addNewBookListenner: (View?, Book) -> Unit,
    private val removeBookListenner: (View?, Book) -> Unit
) :
    RecyclerView.Adapter<CartViewHolder>() {
    var bookList: List<Book> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val dataBinding = ItemCartBinding.inflate(inflater, parent, false)
        return CartViewHolder(dataBinding)
    }

    override fun getItemCount() = bookList.count()

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.setup(bookList[position], addNewBookListenner, removeBookListenner)
    }

    fun updateRepoList(repoList: List<Book>) {
        this.bookList = repoList
        notifyDataSetChanged()
    }
}