package com.example.coffeeapp.fragments.map

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.coffeeapp.R
import com.example.coffeeapp.databinding.FragmentEnterBinding
import com.example.coffeeapp.databinding.FragmentYandexMapBinding
import com.example.coffeeapp.viewmodel.CoffeeViewModel

class YandexMapFragment : Fragment() {

    private lateinit var binding: FragmentYandexMapBinding
    private lateinit var viewModel: CoffeeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentYandexMapBinding.inflate(inflater)

        return binding.root
    }

}