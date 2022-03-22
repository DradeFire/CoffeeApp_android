package com.example.coffeeapp.fragments.nearcoffes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeeapp.MainActivity
import com.example.coffeeapp.R
import com.example.coffeeapp.api.gps.GPSlocationHelper
import com.example.coffeeapp.api.retrofit.json.Locations_Answ
import com.example.coffeeapp.api.retrofit.json.LogRegJSON_Send
import com.example.coffeeapp.databinding.FragmentNearCoffesBinding
import com.example.coffeeapp.viewmodel.CoffeeViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Response

class NearCoffesFragment : Fragment() {

    private lateinit var binding: FragmentNearCoffesBinding
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var viewModel: CoffeeViewModel
    private val gpsHelper by lazy{
        GPSlocationHelper(requireActivity() as MainActivity, fusedLocationProviderClient)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNearCoffesBinding.inflate(inflater)
        requireActivity().findViewById<TextView>(R.id.tx_toolbar_title).text = "Ближайшие кофейни"
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        viewModel = ViewModelProvider(requireActivity())[CoffeeViewModel::class.java]

        testFunc()
        bindObserver()
        bindButton()

        return binding.root
    }

    private fun testFunc() {
//        viewModel.postRequestRegistration(
//            LogRegJSON_Send(
//                "jorik123444@gmail.com",
//                "12345"
//            )
//        )
//        viewModel.regRequestLivedata.observe(viewLifecycleOwner) { response ->
//            when {
//                response.code() == 200 -> {
//                    viewModel.authToken = response.body()?.token!!
//                    Toast.makeText(requireContext(), "Успешная регистрация!", Toast.LENGTH_SHORT).show()
//                    // TODO: Продолжение
//                }
//                response.code() == 400 -> {
//                    Toast.makeText(requireContext(), "Ошибка в запросе!", Toast.LENGTH_SHORT).show()
//                }
//                response.code() == 406 -> {
//                    Toast.makeText(requireContext(), "Такой логин уже используется!", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//        Thread.sleep(5000)
//
        viewModel.postRequestLogin(
            LogRegJSON_Send(
                "jorik123444@gmail.com",
                "12345"
            )
        )
        viewModel.loginRequestLivedata.observe(viewLifecycleOwner) { response ->
            when {
                response.code() == 200 -> {
                    viewModel.authToken = response.body()?.token!!
                    Toast.makeText(requireContext(), "Успешный вход!", Toast.LENGTH_SHORT).show()
                    // TODO: Продолжение
                }
                response.code() == 400 -> {
                    Toast.makeText(requireContext(), "Ошибка в запросе!", Toast.LENGTH_SHORT).show()
                }
                response.code() == 404 -> {
                    Toast.makeText(requireContext(), "Пользователь не существует!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun bindObserver() {
        viewModel.locationsRequestLivadata.observe(viewLifecycleOwner){ response ->
            if(response.code() == 200){
                bindRcView(response)
            }
        }
    }

    private fun bindRcView(response: Response<Locations_Answ>) {
        binding.rcNearCoffes.adapter = AdapterNearCoffes(response.body()!!,
            lat = viewModel.latUser!!, lon = viewModel.lonUser!!
        )
        binding.rcNearCoffes.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun bindButton() {
        binding.btOpenOrder.setOnClickListener {

        }
    }

    override fun onResume() {
        super.onResume()
        gpsHelper.getLastLocation(viewModel)
        lifecycleScope.launch(Dispatchers.IO) {
            delay(2000)
            viewModel.getLocations()
        }

    }

}