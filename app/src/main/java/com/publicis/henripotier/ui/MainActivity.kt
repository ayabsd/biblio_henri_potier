package com.publicis.henripotier.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.publicis.henripotier.R
import com.publicis.henripotier.databinding.ActivityMainBinding
import com.publicis.henripotier.ui.cart.CartFragment
import com.publicis.henripotier.ui.listbooks.BookListFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Aya Boussaadia on 04,September,2020
 */

class MainActivity : AppCompatActivity() {
    private lateinit var viewDataBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewDataBinding.setLifecycleOwner(this)
        bottom_navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        openFragment(BookListFragment.newInstanceFragment("", ""));


    }

    var navigationItemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener =
        object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.getItemId()) {
                    R.id.navigation_home -> {
                        openFragment(BookListFragment.newInstanceFragment("", ""))
                        return true
                    }

                    R.id.navigation_panier -> {
                        openFragment(CartFragment.newInstanceFragment("", ""))
                        return true
                    }
                }
                return false
            }
        }


    fun openFragment(fragment: Fragment?) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment!!)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}