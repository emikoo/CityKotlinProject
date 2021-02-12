package com.example.citykotlinproject.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.citykotlinproject.R
import com.example.citykotlinproject.models.City
import com.example.citykotlinproject.ui.main.adapter.CityAdapter
import kotlinx.android.synthetic.main.activity_main.*

interface RequestResult {
    fun onFailure(fail: String)
    fun onSuccess(result: MutableList<City>)
}
class MainActivity : AppCompatActivity(), RequestResult {

    lateinit var adapter: CityAdapter
    private lateinit var repository: MainRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRV()
        setupRepository()
        setupListener()
    }

    private fun setupRepository(){
        repository = MainRepository(this)
    }

    private fun setupRV(){
        adapter = CityAdapter()
        cities_rv.adapter = adapter
        cities_rv.layoutManager = LinearLayoutManager(this)
    }

    private fun setupListener() {
        search_btn.setOnClickListener {
            var name = field_et.text.toString()
            repository.fetchCity(name)
        }
    }

    override fun onFailure(fail: String) {
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess(result: MutableList<City>) {
        adapter.addItems(result)
    }
}