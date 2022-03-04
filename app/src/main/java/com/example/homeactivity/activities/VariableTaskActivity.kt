package com.example.homeactivity.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.homeactivity.databinding.ActivityVariableTaskBinding

import com.example.homeactivity.viewModels.VariableTaskViewModel
import kotlinx.coroutines.flow.collect

class VariableTaskActivity : AppCompatActivity() {
    private var _binding: ActivityVariableTaskBinding? = null
    private val binding get() = _binding!!
    private lateinit var variableTaskViewModel: VariableTaskViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityVariableTaskBinding.inflate(layoutInflater)
        variableTaskViewModel = ViewModelProvider(this).get(VariableTaskViewModel::class.java)
        setContentView(binding.root)

        binding.apply {
            emailEditText.addTextChangedListener(object : TextWatcher {

                override fun afterTextChanged(s: Editable) {}

                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(
                    s: CharSequence,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                    variableTaskViewModel.changeEmail(s.toString())
                    variableTaskViewModel.changeButtonState()

                }
            })

            nameEditText.addTextChangedListener(object : TextWatcher {

                override fun afterTextChanged(s: Editable) {}

                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(
                    s: CharSequence,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                    variableTaskViewModel.changeName(s.toString())
                    variableTaskViewModel.changeButtonState()


                }
            })

            ageEditText.addTextChangedListener(object : TextWatcher {

                override fun afterTextChanged(s: Editable) {}

                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(
                    s: CharSequence,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                    variableTaskViewModel.changeAge(s.toString())
                    variableTaskViewModel.changeButtonState()
//
                }
            })
        }


        lifecycleScope.launchWhenStarted {

            variableTaskViewModel.variableTaskButtonState.collect {
                when (it) {
                    is VariableTaskViewModel.VariableTaskButtonState.Enable -> {
                        binding.nextButton.isEnabled = true
                    }
                    is VariableTaskViewModel.VariableTaskButtonState.Disable -> {
                        binding.nextButton.isEnabled = false
                    }
                    is VariableTaskViewModel.VariableTaskButtonState.Empty -> {
                        binding.nextButton.isEnabled = false
                    }

                }

            }
        }

    }
}