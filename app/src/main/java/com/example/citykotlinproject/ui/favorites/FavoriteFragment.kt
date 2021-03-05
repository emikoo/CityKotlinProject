package com.example.citykotlinproject.ui.favorites

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.citykotlinproject.R
import com.example.citykotlinproject.models.City
import com.example.citykotlinproject.ui.city.RequestResult
import com.example.citykotlinproject.ui.city.adapter.CityAdapter
import com.example.citykotlinproject.ui.city.adapter.CityAdapter.Companion.favoriteFragment
import com.example.citykotlinproject.ui.main.MainRepository
import kotlinx.android.synthetic.main.custom_dialog.*
import kotlinx.android.synthetic.main.custom_dialog.view.*
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : Fragment(), CityAdapter.ClickListener, RequestResult {

    // добавить плейсхолдер

    private lateinit var repository: MainRepository
    private lateinit var adapter: CityAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupRepository()
    }

    override fun onResume() {
        super.onResume()
        fetchFromDatabase()
    }

    private fun setupRecyclerView() {
        adapter = CityAdapter(this, favoriteFragment)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setupRepository(){
        repository = MainRepository(this)
    }

    private fun fetchFromDatabase() {
        repository.fetchFavoriteCity()
    }

    override fun OnItemLongClick(item: City) {
        showAlertDialog(item)
    }

    private fun showAlertDialog(item: City) {
        val alert = AlertDialog.Builder(requireContext())
        val view: View = layoutInflater.inflate(R.layout.custom_dialog, null)
        val dialog = alert.create()
        view.positive_btn.text = "YES"
        view.negative_btn.text = "NO"
        alert.setView(view).setCustomTitle(title_dialog)
            .setCancelable(false)
        view.positive_btn.setOnClickListener {
            repository.deleteCity(item)
        }
        view.negative_btn.setOnClickListener {
            dialog.dismiss()
        }
        alert.show()
    }

    override fun onFailure(t: Throwable) {
    }

    override fun <T> onSuccess(result: T) {
    }
}