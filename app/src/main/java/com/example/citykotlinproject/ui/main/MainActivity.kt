package com.example.citykotlinproject.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.citykotlinproject.R
import com.example.citykotlinproject.models.City
import com.example.citykotlinproject.ui.city.CityFragment
import com.example.citykotlinproject.ui.city.adapter.CityAdapter
import com.example.citykotlinproject.ui.favorites.FavoriteFragment
import com.example.citykotlinproject.ui.main.adapter.MainViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_city.*

class MainActivity : AppCompatActivity() {

    lateinit var adapter: MainViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewPager()
        setupBottomNavigation()
    }

    private fun setupViewPager(){
        adapter = MainViewPagerAdapter(this)
        adapter.addFragments(CityFragment())
        adapter.addFragments(FavoriteFragment())
        view_pager.adapter = adapter
        view_pager.offscreenPageLimit = 3
        view_pager.isEnabled = false
        view_pager.isUserInputEnabled = false
    }

    private fun setupBottomNavigation() {
        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.search -> changeCurrentItem(0)
                R.id.favorites -> changeCurrentItem(1)
            }
            true
        }
    }

    private fun changeCurrentItem(position: Int) {
        view_pager.setCurrentItem(position, false)
    }
}