package com.example.coffeeapp.fragments.order

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeapp.databinding.ItemCoffeeOrderBinding
import com.example.coffeeapp.viewmodel.CoffeeViewModel

class AdapterOrder(private val viewModel: CoffeeViewModel)
    : RecyclerView.Adapter<AdapterOrder.ItemViewHolder>() {

    private var menuList = emptyArray<String>()

    class ItemViewHolder(private val binding: ItemCoffeeOrderBinding)
        : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: String) = with(binding){
//            txNameProductOrder.text = item.name
//            txSellCountOrder.text = "${item.price} руб."

            bindButtons(binding)
        }

        private fun bindButtons(binding: ItemCoffeeOrderBinding) {
            binding.btMinusCountMenu.setOnClickListener {

            }
            binding.btPlusOrderMenu.setOnClickListener {

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemCoffeeOrderBinding
                .inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = menuList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

}