package com.example.coffeeapp.fragments.menu

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coffeeapp.api.retrofit.json.Menu_AnswItem
import com.example.coffeeapp.databinding.ItemCoffeeMenuBinding

class AdapterMenu (menuList_out: ArrayList<Menu_AnswItem>, private val myFragment: MenuCoffesFragment)
    : RecyclerView.Adapter<AdapterMenu.ItemViewHolder>() {

    private var menuList = menuList_out

    class ItemViewHolder(private val binding: ItemCoffeeMenuBinding)
        : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: Menu_AnswItem, myFragment: MenuCoffesFragment) = with(binding){
            txNameProduct.text = item.name
            txSellCount.text = "${item.price} руб."

            Glide
                .with(myFragment)
                .load(item.imageURL)
                .centerCrop()
                .into(imProduct)

            bindButtons(binding)
        }

        private fun bindButtons(binding: ItemCoffeeMenuBinding) {
            binding.btMinusCountMenu.setOnClickListener {

            }
            binding.btPlusOrderMenu.setOnClickListener {

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemCoffeeMenuBinding
            .inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = menuList[position]
        holder.bind(item, myFragment)
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

}