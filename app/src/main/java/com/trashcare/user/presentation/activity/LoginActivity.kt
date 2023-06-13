package com.trashcare.user.presentation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.trashcare.user.R
import com.trashcare.user.data.model.request.LoginRequestBody
import com.trashcare.user.databinding.ActivityLoginBinding
import com.trashcare.user.presentation.viewmodel.AuthViewModel
import com.trashcare.user.utils.EmailValidation.isEmailValid
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val authViewModel: AuthViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpLoginAction()
        setupObserver()

        val token = authViewModel.getToken()
        if (token != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }


    private fun setUpLoginAction() {
        binding.btnLogin.setOnClickListener {
            val email = binding.edEmailLogin.text.toString()
            val password = binding.edPasswordLogin.text.toString()
            when {
                email.isEmpty() -> {
                    binding.edEmailLogin.error = resources.getString(R.string.emailEmptyError)
                }
                password.isEmpty() -> {
                    binding.edPasswordLogin.error = resources.getString(R.string.passwordEmptyError)
                }
                !isEmailValid(email) -> {
                    binding.edEmailLogin.error = resources.getString(R.string.formatEmailWrong)
                }
                password.length < 6 -> {
                    binding.edPasswordLogin.error = resources.getString(R.string.lengthPasswordWrong)
                }
                else -> {
                    authViewModel.loginUser(LoginRequestBody(email, password))
                }
            }
        }
        binding.tvRegLog.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupObserver() {
        authViewModel.loginUser.observe(this) { response ->
            if (response != null){
                val token = response.token
                Log.d("LoginActivity", "Received token: $token")
                authViewModel.saveToken(token)
                Toast.makeText(this, "Login success", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Token null", Toast.LENGTH_SHORT).show()
            }
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