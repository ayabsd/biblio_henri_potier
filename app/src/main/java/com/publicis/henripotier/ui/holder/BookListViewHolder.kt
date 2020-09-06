package com.publicis.henripotier.ui.holder

import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.publicis.henripotier.model.Book
import com.publicis.henripotier.ui.listbooks.BookListViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_book.view.*
import org.jetbrains.anko.bundleOf




class BookListViewHolder constructor(private val dataBinding: ViewDataBinding, private val repoListViewModel: BookListViewModel)
    : RecyclerView.ViewHolder(dataBinding.root) {
    val avatarImage = itemView.product_thumb
    fun setup(itemData: Book) {
        dataBinding.setVariable(BR.itemBook, itemData)
        dataBinding.executePendingBindings()
        Picasso.get().load(itemData.cover).into(avatarImage);


    }
}