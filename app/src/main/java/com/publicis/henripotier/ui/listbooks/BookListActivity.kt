package com.publicis.henripotier.ui.listbooks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.publicis.henripotier.R
import com.publicis.henripotier.databinding.ActivityBooksListBinding
import com.publicis.henripotier.ui.adapter.BooksListAdapter
import kotlinx.android.synthetic.main.activity_books_list.*
import org.jetbrains.anko.longToast

/**
 * Created by Aya Boussaadia on 04,September,2020
 */
class BookListActivity : AppCompatActivity() {

    private lateinit var viewModel: BookListViewModel
    private lateinit var viewDataBinding: ActivityBooksListBinding
    private lateinit var adapter: BooksListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_books_list)
        viewModel = ViewModelProvider(this)[BookListViewModel::class.java]
        viewDataBinding.viewmodel = viewModel
        viewDataBinding.viewmodel?.fetchBookList()
        viewDataBinding.setLifecycleOwner(this)


        setupAdapter()
        setupObservers()
    }

    private fun setupAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            adapter = BooksListAdapter(this, viewDataBinding.viewmodel!!)
            val layoutManager = LinearLayoutManager(this)
            repo_list_rv.layoutManager = layoutManager
            repo_list_rv.addItemDecoration(DividerItemDecoration(this, layoutManager.orientation))
            repo_list_rv.adapter = adapter
        }
    }

    private fun setupObservers() {
        viewDataBinding.viewmodel?.bookList?.observe(this, Observer {
            adapter.updateRepoList(it)
        })

        viewDataBinding.viewmodel?.toastMessage?.observe(this, Observer {
            this?.longToast(it)
        })
    }

}
