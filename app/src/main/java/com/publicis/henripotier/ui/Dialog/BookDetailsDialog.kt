package com.publicis.henripotier.ui.Dialog

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.publicis.henripotier.R
import com.publicis.henripotier.model.Book
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.book_details_dialog.*

/**
 * Created by Aya Boussaadia on 06,September,2020
 */
class BookDetailsDialog {
    class BookDetailDialog : AppCompatDialogFragment() {
        private lateinit var book: Book

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
             book = arguments!!.getParcelable(keyBook)!!
        }



        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            title.text = book.title
            synopsis.text = TextUtils.join("\n\n", book.synopsis!!)
            Glide
                .with(image.context)
                .load(book.cover)
                .apply(RequestOptions().placeholder(R.drawable.ic_launcher_foreground))
                .into(image)
            close.setOnClickListener { v1 -> dismiss() }
        }

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.book_details_dialog, container, true)
        }

        companion object {
            var TAG = "BookDetails"
            var keyBook = "Book"
            fun newInstance(book: Book): BookDetailDialog {
                val bookDetailsDialog = BookDetailDialog()
                val bundle = Bundle()
                bundle.putParcelable(keyBook, book)
                bookDetailsDialog.setArguments(bundle)
                return bookDetailsDialog
            }
        }
    }}
