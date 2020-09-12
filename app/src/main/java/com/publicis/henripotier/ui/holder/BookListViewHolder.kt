package com.publicis.henripotier.ui.holder

import android.content.Context
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.publicis.henripotier.model.Book
import com.publicis.henripotier.ui.dialog.BookDetailsDialog
import com.publicis.henripotier.ui.listbooks.BookListViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_book.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick


class BookListViewHolder constructor(
    val mContext: Context,
    private val dataBinding: ViewDataBinding,
    private val repoListViewModel: BookListViewModel
) : RecyclerView.ViewHolder(dataBinding.root) {
    val avatarImage = itemView.product_thumb


    fun setup(
        itemData: Book,
        addNewBookListenner: (View?, Book) -> Unit
    ) {
        dataBinding.setVariable(BR.itemBook, itemData)
        dataBinding.executePendingBindings()
        Picasso.get().load(itemData.cover).into(avatarImage);

        itemView.add_item.setOnClickListener({ v ->
            addNewBookListenner(v, itemData)

        })

        itemView.onClick {
            val fragmentTransaction =
                (mContext as AppCompatActivity).supportFragmentManager.beginTransaction()
            val spFragment =
                mContext.supportFragmentManager.findFragmentByTag(BookDetailsDialog.BookDetailDialog.TAG)
            if (spFragment != null) {
                fragmentTransaction.remove(spFragment)
            }
            fragmentTransaction.addToBackStack(null)
            val myDialog = BookDetailsDialog.BookDetailDialog.newInstance(itemData)
            myDialog.show(fragmentTransaction, BookDetailsDialog.BookDetailDialog.TAG)
        }

    }

}