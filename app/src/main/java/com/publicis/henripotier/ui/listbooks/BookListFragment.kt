package com.publicis.henripotier.ui.listbooks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.publicis.henripotier.Injection.Injection
import com.publicis.henripotier.databinding.FragmentBooksListBinding
import com.publicis.henripotier.model.Book
import com.publicis.henripotier.ui.adapter.BooksListAdapter
import kotlinx.android.synthetic.main.fragment_books_list.*
import org.jetbrains.anko.longToast


/**
 * Created by Aya Boussaadia on 04,September,2020
 */
class BookListFragment : Fragment() {


    private lateinit var viewModel: BookListViewModel
    private lateinit var viewDataBinding: FragmentBooksListBinding
    private lateinit var adapter: BooksListAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentBooksListBinding.inflate(inflater, container, false).apply {

            viewmodel = ViewModelProviders.of(
                this@BookListFragment,
                Injection.provideViewModelFactoryBookList(activity!!)
            )
                .get(BookListViewModel::class.java)
            setLifecycleOwner(viewLifecycleOwner)


        }


        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.viewmodel?.fetchBookList()
        setupAdapter()
        setupObservers()

    }


    private fun setupAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            adapter = BooksListAdapter(
                activity!!,
                viewDataBinding.viewmodel!!,
                { view: View?, book: Book ->
                    viewDataBinding.viewmodel?.addBook(book)

                }
            )
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
        viewDataBinding.viewmodel!!.bookList.observe(this, Observer<List<Book>> {
            adapter.updateRepoList(it)
            if (it != null) {
                if (it.isNotEmpty()) {

                    //  totalPriceTxt.text = resources.getString(R.string.price_processing)
                    // viewModelBasketList.getAllOffers(it)
                } else {
                    //totalPriceTxt.text = resources.getString(R.string.empty_basket)
                }
            }
        })

        viewDataBinding.viewmodel?.toastMessage?.observe(activity!!, Observer {
            activity?.longToast(it)
        })
    }

    companion object {

        fun newInstanceFragment(param1: String?, param2: String?): BookListFragment? {
            val fragment = BookListFragment()
            val args = Bundle()
            args.putString("param1", param1)
            args.putString("param2", param2)
            fragment.setArguments(args)
            return fragment
        }
    }


}
