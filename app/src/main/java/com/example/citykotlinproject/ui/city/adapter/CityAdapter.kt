package com.example.citykotlinproject.ui.city.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.citykotlinproject.R
import com.example.citykotlinproject.models.City
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import kotlinx.android.synthetic.main.custom_dialog.view.*
import kotlinx.android.synthetic.main.item_city.view.*
import kotlinx.android.synthetic.main.item_empty.view.*


class CityAdapter(private var listener: ClickListener): RecyclerView.Adapter<BaseViewHolder>() {

    private var array = mutableListOf<City>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return if (viewType == VIEW_TYPE_DATA) CityViewHolder (
            LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false)
        ) else EmptyViewHolder (
            LayoutInflater.from(parent.context).inflate(R.layout.item_empty, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return if (array.count() == 0) 1
        else array.count()
    }

    override fun getItemViewType(position: Int): Int {
        return if (array.count() == 0) VIEW_TYPE_EMPTY
        else VIEW_TYPE_DATA
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val type = getItemViewType(position)
        if (type == VIEW_TYPE_DATA) setupCityViewHolder(holder as CityViewHolder, position)
        else setupEmptyViewHolder(holder as EmptyViewHolder)
    }

    private fun setupCityViewHolder(holder: CityViewHolder, position: Int){
        val item = array[position]
        holder.bind(item)
        holder.itemView.setOnLongClickListener {
            listener.OnItemLongClick(item)
            true
        }
    }

    private fun setupEmptyViewHolder(holder: EmptyViewHolder) {
        holder.bind("У вас нет данных в избранном", R.drawable.ic_star)
    }

    fun addItems(item: MutableList<City>){
        array = item
        notifyDataSetChanged()
    }

    fun addItem(item: City) {
        array.add(item)
        notifyItemInserted(array.lastIndex)
    }

    interface ClickListener{
        fun OnItemLongClick(item: City)
    }

    companion object {
        val VIEW_TYPE_EMPTY = 1
        val VIEW_TYPE_DATA = 2
    }
}

open class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

class CityViewHolder(itemView: View) : BaseViewHolder(itemView) {
    fun bind(item: City) {
        itemView.name_tv.text = item.name
        itemView.capital_tv.text = item.capital
        GlideToVectorYou.init().with(itemView.flag_iv.context)
            .load(Uri.parse(item.flag), itemView.flag_iv)
    }
}

class EmptyViewHolder(itemView: View) : BaseViewHolder(itemView) {
    fun bind(title: String, icHourglassEmpty: Int) {
        itemView.title.text= title
        itemView.image_placeholder.setImageResource(icHourglassEmpty)
    }
}
