package com.example.citykotlinproject.ui.city

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.citykotlinproject.R
import com.example.citykotlinproject.models.City
import com.example.citykotlinproject.ui.city.adapter.CityAdapter
import com.example.citykotlinproject.ui.main.MainRepository
import kotlinx.android.synthetic.main.fragment_city.*


interface RequestResult {
    fun onFailure(fail: String)
    fun onSuccess(result: MutableList<City>)
}

class CityFragment : Fragment(), RequestResult {

    private lateinit var adapter: CityAdapter
    private lateinit var repository: MainRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_city, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        setupRepository()
        setupTextWatcher()
    }

    private fun setupRecyclerView() {
        adapter = CityAdapter()
        cities_rv.setHasFixedSize(true)
        cities_rv.adapter = adapter
        cities_rv.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setupRepository() {
        repository = MainRepository(this)
    }

    private fun setupTextWatcher() {
        field_et.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }
            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                if (s.toString() == "") {
                    repository.fetchAll()
                } else {
                    // perform search
                    val textl: String = field_et.getText().toString().trim()
                    repository.fetchCity(textl)
                }
            }
            override fun afterTextChanged(s: Editable) {}
        })
    }

    override fun onFailure(fail: String) {
        Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess(result: MutableList<City>) {
        adapter.addItems(result)
    }
}