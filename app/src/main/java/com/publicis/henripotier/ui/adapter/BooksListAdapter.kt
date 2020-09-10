package com.publicis.henripotier.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.publicis.henripotier.databinding.ItemBookBinding
import com.publicis.henripotier.model.Book
import com.publicis.henripotier.ui.holder.BookListViewHolder
import com.publicis.henripotier.ui.listbooks.BookListViewModel

/**
 * Created by Aya Boussaadia on 04,September,2020
 */


class BooksListAdapter(val mContext: Context, private val repoListViewModel: BookListViewModel , private val addNewBookListenner: (View?, Book) -> Unit ) :
    RecyclerView.Adapter<BookListViewHolder>() {
    var bookList: List<Book> = emptyList()
    var number = 0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val dataBinding = com.publicis.henripotier.databinding.ItemBookBinding.inflate(inflater, parent, false)
        return BookListViewHolder(mContext, dataBinding, repoListViewModel)
    }

    override fun getItemCount() = bookList.count()

    override fun onBindViewHolder(holder: BookListViewHolder, position: Int) {
        bookList[position].cartSize = number
        holder.setup( bookList[position] , addNewBookListenner )


    }

    fun updateRepoList(repoList: List<Book>) {
        this.bookList = repoList
        notifyDataSetChanged()
    }



}