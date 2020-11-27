package com.alodok.testapp.login

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alodok.testapp.HomeMainActivity
import com.alodok.testapp.databinding.ActivityLoginBinding
import com.alodok.testapp.helper.PreferenceHelper
import com.alodok.testapp.helper.PreferenceHelper.isLogin
import com.alodok.testapp.helper.PreferenceHelper.nameuser
import com.alodok.testapp.helper.Utility
import com.alodok.testapp.signup.SignupActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val PREFERENCE_NAME = "Preference"
        val prefs = PreferenceHelper.customPreference(this, PREFERENCE_NAME)

        println("loginyaaa ==> ${prefs.isLogin.toString()}")
        checkUserLogin(prefs)

    }

    private fun checkUserLogin(prefs: SharedPreferences) {

        if (prefs.isLogin.equals("true")) {
            val intent = Intent(this, HomeMainActivity::class.java)
            startActivity(intent)
        } else {
            binding = ActivityLoginBinding.inflate(layoutInflater)
            setContentView(binding.root)

            binding.btnLogin.setOnClickListener {
                if (binding.edtUsername.text.toString().trim().isNotEmpty() &&
                    binding.edtPassword.text.toString().trim().isNotEmpty()
                ) {
                    prefs.nameuser = binding.edtUsername.text.toString().trim()
                    prefs.isLogin = "true"

                    val intent = Intent(this, HomeMainActivity::class.java)
                    startActivity(intent)

                    binding.edtPassword.text = null
                    binding.edtPassword.text = null

                } else {
                    Utility().pesan(baseContext, "Username dan Password harus diisi.")
                }
            }

            binding.btnSignup.setOnClickListener {
                val intent = Intent(this, SignupActivity::class.java)
                startActivity(intent)
            }
        }
    }
}