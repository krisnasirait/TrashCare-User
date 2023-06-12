package com.trashcare.user.presentation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.trashcare.user.R
import com.trashcare.user.data.model.request.RegisterRequestBody
import com.trashcare.user.databinding.ActivityRegisterBinding
import com.trashcare.user.presentation.viewmodel.AuthViewModel
import com.trashcare.user.utils.EmailValidation
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val authViewModel: AuthViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRegisterAction()
        setupObserver()

    }

    private fun setUpRegisterAction(){
        binding.btnRegister.setOnClickListener {
            val email = binding.edEmailRegister.text.toString()
            val password = binding.edPasswordRegister.text.toString()
            when {
                email.isEmpty() -> {
                    binding.edEmailRegister.error = resources.getString(R.string.emailEmptyError)
                }
                password.isEmpty() -> {
                    binding.edPasswordRegister.error = resources.getString(R.string.passwordEmptyError)
                }
                !EmailValidation.isEmailValid(email) -> {
                    binding.edEmailRegister.error = resources.getString(R.string.formatEmailWrong)
                }
                password.length < 6 -> {
                    binding.edPasswordRegister.error = resources.getString(R.string.lengthPasswordWrong)
                }
                else -> {
                   authViewModel.registerUser(RegisterRequestBody(email, password))
                }
            }
        }
    }

    private fun setupObserver() {
        authViewModel.registerUser.observe(this) {
            Toast.makeText(this, "Register success", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


        authViewModel.errorMessage.observe(this) { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }

        authViewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun showLoading(isLoading:Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.VISIBLE
        }
    }
}