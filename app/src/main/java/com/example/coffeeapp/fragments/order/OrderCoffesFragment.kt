package com.example.coffeeapp.fragments.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.coffeeapp.R
import com.example.coffeeapp.databinding.FragmentEnterBinding
import com.example.coffeeapp.databinding.FragmentOrderCoffesBinding
import com.example.coffeeapp.viewmodel.CoffeeViewModel

class OrderCoffesFragment : Fragment() {

    private lateinit var binding: FragmentOrderCoffesBinding
    private lateinit var viewModel: CoffeeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderCoffesBinding.inflate(inflater)

        return binding.root
    }

}