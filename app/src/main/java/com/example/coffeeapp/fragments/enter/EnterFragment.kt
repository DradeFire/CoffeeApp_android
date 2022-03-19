package com.example.coffeeapp.fragments.enter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.coffeeapp.R
import com.example.coffeeapp.api.retrofit.json.LogRegJSON_Send
import com.example.coffeeapp.databinding.FragmentEnterBinding
import com.example.coffeeapp.viewmodel.CoffeeViewModel

class EnterFragment : Fragment() {

    private lateinit var binding: FragmentEnterBinding
    private lateinit var viewModel: CoffeeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEnterBinding.inflate(inflater)
        requireActivity().findViewById<TextView>(R.id.tx_toolbar_title).text = "Вход"
        viewModel = ViewModelProvider(requireActivity())[CoffeeViewModel::class.java]

        bindButton()
        binObserver()

        return binding.root
    }

    private fun binObserver() {
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

    private fun bindButton() {

        binding.btEnter.setOnClickListener {
            if(checkFields() and isEmailValid()){
                viewModel.postRequestLogin(
                    LogRegJSON_Send(
                        binding.inputEmailEnter.text.toString(),
                        binding.inputPassEnter.text.toString()
                    )
                )
            }
        }
    }

    private fun isEmailValid(): Boolean {
        val isValid = android.util.Patterns.EMAIL_ADDRESS.matcher(binding.inputEmailEnter.text.toString()).matches()
        return if(isValid){
            isValid
        } else{
            Toast.makeText(requireContext(), "Формат почты некорректный!", Toast.LENGTH_SHORT).show()
            isValid
        }
    }

    private fun checkFields(): Boolean = with(binding) {
        val isNotEmptyFields = inputEmailEnter.text.isNotEmpty() and inputPassEnter.text.isNotEmpty()
        return if(isNotEmptyFields){
            isNotEmptyFields
        } else{
            Toast.makeText(requireContext(), "Присутствуют пустые поля!", Toast.LENGTH_SHORT).show()
            isNotEmptyFields
        }
    }

}