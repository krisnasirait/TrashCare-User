package com.trashcare.user.presentation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.trashcare.user.R
import com.trashcare.user.databinding.ActivityRegisterBinding
import com.trashcare.user.utils.EmailValidation

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRegisterAction()
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
                    // sementara
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}