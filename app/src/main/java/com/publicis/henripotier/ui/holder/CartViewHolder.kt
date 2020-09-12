package com.publicis.henripotier.ui.holder

import android.content.Context
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.publicis.henripotier.model.Book
import com.publicis.henripotier.ui.cart.CartViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_book.view.*
import kotlinx.android.synthetic.main.item_book.view.product_thumb
import kotlinx.android.synthetic.main.item_cart.view.*

/**
 * Created by Aya Boussaadia on 10,September,2020
 */

class CartViewHolder constructor(
    private val dataBinding: ViewDataBinding
) : RecyclerView.ViewHolder(dataBinding.root) {
    val avatarImage = itemView.product_thumb

    fun setup(
        itemData: Book,
        addNewBookListenner: (View?, Book) -> Unit,
        removeBookListenner: (View?, Book) -> Unit
    ) {
        dataBinding.setVariable(BR.itemCart, itemData)
        dataBinding.executePendingBindings()

        Picasso.get().load(itemData.cover).into(avatarImage);

        itemView.add_cart.setOnClickListener({ v ->
            addNewBookListenner(v, itemData)
        })
        itemView.remove_cart.setOnClickListener({ v ->
            removeBookListenner(v, itemData)

        })

    }
}
