package com.example.coffeeapp.fragments.registration

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
import com.example.coffeeapp.databinding.FragmentRegistrationBinding
import com.example.coffeeapp.viewmodel.CoffeeViewModel

class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding
    private lateinit var viewModel: CoffeeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationBinding.inflate(inflater)
        requireActivity().findViewById<TextView>(R.id.tx_toolbar_title).text = "Регистрация"
        viewModel = ViewModelProvider(requireActivity())[CoffeeViewModel::class.java]

        bindButton()
        binObserver()

        return binding.root
    }

    private fun binObserver() {
        viewModel.regRequestLivedata.observe(viewLifecycleOwner) { response ->
            when {
                response.code() == 200 -> {
                    viewModel.authToken = response.body()?.token!!
                    Toast.makeText(requireContext(), "Успешная регистрация!", Toast.LENGTH_SHORT).show()
                    // TODO: Продолжение
                }
                response.code() == 400 -> {
                    Toast.makeText(requireContext(), "Ошибка в запросе!", Toast.LENGTH_SHORT).show()
                }
                response.code() == 406 -> {
                    Toast.makeText(requireContext(), "Такой логин уже используется!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun bindButton() {

        binding.btReg.setOnClickListener {
            if(checkPasswords() and checkFields() and isEmailValid()){
                viewModel.postRequestRegistration(
                    LogRegJSON_Send(
                        binding.inputEmailReg.text.toString(),
                        binding.inputPassReg.text.toString()
                    )
                )
            }
        }
    }

    private fun isEmailValid(): Boolean {
        val isValid = android.util.Patterns.EMAIL_ADDRESS.matcher(binding.inputEmailReg.text.toString()).matches()
        return if(isValid){
            isValid
        } else{
            Toast.makeText(requireContext(), "Формат почты некорректный!", Toast.LENGTH_SHORT).show()
            isValid
        }
    }

    private fun checkFields(): Boolean = with(binding) {
        val isNotEmptyFields = inputEmailReg.text.isNotEmpty() and inputPassReg.text.isNotEmpty() and inputPassSecondReg.text.isNotEmpty()
        return if(isNotEmptyFields){
            isNotEmptyFields
        } else{
            Toast.makeText(requireContext(), "Присутствуют пустые поля!", Toast.LENGTH_SHORT).show()
            isNotEmptyFields
        }
    }

    private fun checkPasswords(): Boolean {
        val isEq = binding.inputPassReg.text.toString() == binding.inputPassSecondReg.text.toString()
        return if(isEq){
            isEq
        } else{
            Toast.makeText(requireContext(), "Пароли не сходятся!", Toast.LENGTH_SHORT).show()
            isEq
        }
    }

}