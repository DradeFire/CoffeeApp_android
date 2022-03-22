package com.example.coffeeapp.fragments.nearcoffes

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeapp.api.retrofit.json.Locations_AnswItem
import com.example.coffeeapp.databinding.ItemCoffeeHouseBinding
import kotlin.math.cos
import kotlin.math.sqrt

class AdapterNearCoffes(locationList_out: ArrayList<Locations_AnswItem>, private val lat: Double, private val lon: Double)
    : RecyclerView.Adapter<AdapterNearCoffes.ItemViewHolder>() {

    private var locationList = locationList_out

    class ItemViewHolder(private val binding: ItemCoffeeHouseBinding)
        : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: Locations_AnswItem, lat: Double, lon: Double) = with(binding){
            txNameCoffeeHouse.text = item.name
            txDistanceToCoffeeHouse.text = "${calculateDistance(item, lat, lon)}м. от вас"
        }

        private fun calculateDistance(item: Locations_AnswItem, lat: Double, lon: Double): String {
            val lon2: Double = item.point.longitude
            val lat2: Double = item.point.latitude
            val result = 111.2 * sqrt(
                (lon - lon2) * (lon - lon2) + (lat - lat2) * cos(Math.PI * lon / 180) * (lat - lat2) * cos(
                    Math.PI * lon / 180
                )
            )
            return result.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ItemCoffeeHouseBinding
            .inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = locationList[position]
        holder.bind(item, lat, lon)
    }

    override fun getItemCount(): Int {
        return locationList.size
    }
}