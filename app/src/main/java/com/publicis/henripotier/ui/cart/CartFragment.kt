package com.publicis.henripotier.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.publicis.henripotier.Injection.Injection
import com.publicis.henripotier.databinding.FragmentBooksListBinding
import com.publicis.henripotier.databinding.FragmentCartBinding
import com.publicis.henripotier.model.Book
import com.publicis.henripotier.ui.adapter.BooksListAdapter
import com.publicis.henripotier.ui.adapter.CartAdapter
import com.publicis.henripotier.ui.listbooks.BookListFragment
import com.publicis.henripotier.ui.listbooks.BookListViewModel
import kotlinx.android.synthetic.main.fragment_books_list.*
import kotlinx.android.synthetic.main.fragment_books_list.repo_list_rv
import kotlinx.android.synthetic.main.fragment_cart.*
import org.jetbrains.anko.longToast

/**
 * Created by Aya Boussaadia on 10,September,2020
 */


class CartFragment : Fragment() {

    private lateinit var viewModel: CartViewModel
    private lateinit var viewDataBinding: FragmentCartBinding
    private lateinit var adapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentCartBinding.inflate(inflater, container, false).apply {
            cartviewmodel = ViewModelProviders.of(
                this@CartFragment,
                Injection.provideViewModelFactoryCart(activity!!)
            ).get(CartViewModel::class.java)
            setLifecycleOwner(viewLifecycleOwner)

        }

        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupObservers()

    }

    private fun setupAdapter() {
        val viewModel = viewDataBinding.cartviewmodel
        if (viewModel != null) {
            adapter = CartAdapter(

                { view: View?, book: Book ->
                    viewModel.addBook(book)

                },
                { view: View?, book: Book ->

                    viewModel.removeBook(book)


                })
            val layoutManager = LinearLayoutManager(activity)
            repo_list_rv.layoutManager = layoutManager
            repo_list_rv.addItemDecoration(
                DividerItemDecoration(
                    activity,
                    layoutManager.orientation
                )
            )
            repo_list_rv.adapter = adapter
        }
    }

    private fun setupObservers() {
        viewDataBinding.cartviewmodel!!.bookList.observe(this, Observer<List<Book>> {
            adapter.updateRepoList(it)
            if (it != null) {
                if (it.isNotEmpty()) {
                    progress.visibility = View.INVISIBLE
                    empty.visibility = View.INVISIBLE
                    viewDataBinding.cartviewmodel!!.fetchOffers()
                    card_container_price.visibility = View.VISIBLE



                } else {
                    progress.visibility = View.INVISIBLE
                    empty.visibility = View.VISIBLE
                    repo_list_rv.visibility = View.INVISIBLE
                    total_price.visibility = View.INVISIBLE
                    card_container_price.visibility = View.INVISIBLE

                }
            }
        })
    }

    companion object {
        fun newInstanceFragment(param1: String?, param2: String?): CartFragment? {
            val fragment = CartFragment()
            val args = Bundle()
            args.putString("param2", param1)
            args.putString("param2", param2)
            fragment.setArguments(args)
            return fragment
        }
    }
}






