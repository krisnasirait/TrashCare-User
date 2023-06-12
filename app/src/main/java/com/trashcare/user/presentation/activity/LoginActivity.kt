package com.trashcare.user.presentation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.trashcare.user.R
import com.trashcare.user.databinding.ActivityLoginBinding
import com.trashcare.user.utils.EmailValidation.isEmailValid

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpLoginAction()

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
                    val intent =  Intent(this, WelcomeActivity::class.java)
                    startActivity(intent)
                }
            }

            binding.tvRegLog.setOnClickListener {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }
        }
    }
}