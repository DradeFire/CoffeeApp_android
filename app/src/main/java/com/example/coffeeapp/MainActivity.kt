package com.example.coffeeapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.coffeeapp.viewmodel.CoffeeViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val PERMISSION_ID = 1011
    private lateinit var viewModel: CoffeeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(coffee_toolbar)

        viewModel = ViewModelProvider(this)[CoffeeViewModel::class.java]
    }

}