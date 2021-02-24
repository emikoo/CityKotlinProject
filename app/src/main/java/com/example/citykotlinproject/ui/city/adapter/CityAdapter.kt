package com.example.citykotlinproject.ui.city.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.citykotlinproject.R
import com.example.citykotlinproject.models.City
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import kotlinx.android.synthetic.main.item_city.view.*


class CityAdapter: RecyclerView.Adapter<CityViewHolder>() {

    private var array = mutableListOf<City>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        return CityViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return array.count()
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val item = array[position]
        holder.bind(item)
    }

    fun addItems(item: MutableList<City>){
        array = item
        notifyDataSetChanged()
    }
}

class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: City) {
        itemView.name_tv.text = item.name
        itemView.capital_tv.text = item.capital
        GlideToVectorYou.init().with(itemView.flag_iv.context)
            .load(Uri.parse(item.flag), itemView.flag_iv)
    }
}
