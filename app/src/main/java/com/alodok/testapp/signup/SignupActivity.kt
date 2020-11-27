package com.alodok.testapp.signup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.alodok.testapp.database.RoomDB
import com.alodok.testapp.databinding.ActivitySignupBinding
import com.alodok.testapp.helper.Utility
import com.alodok.testapp.login.LoginActivity
import com.alodok.testapp.viewmodel.UserViewModel

class SignupActivity : AppCompatActivity() {

    private lateinit var context: Context
    private lateinit var userViewModel: UserViewModel
    private lateinit var binding: ActivitySignupBinding

    private var isGender = 1
    private val db by lazy { RoomDB }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        title = "Daftar"

        context = this@SignupActivity
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.rbPria.setOnClickListener {
            if (binding.rbPria.isChecked) {
                binding.rbWanita.isChecked = false
                isGender = 1
            }
        }

        binding.rbWanita.setOnClickListener {
            if (binding.rbWanita.isChecked) {
                binding.rbPria.isChecked = false
                isGender = 0
            }
        }

        binding.btnSignup.setOnClickListener {
            validation()
        }
    }

    private fun validation() {
        if (binding.edtUsername.text.toString().trim().isNotEmpty() &&
            binding.edtPassword.text.toString().trim().isNotEmpty() &&
            binding.edtHp.text.toString().trim().isNotEmpty()
        ) {

            if (binding.rbPria.isChecked || binding.rbWanita.isChecked) {
                println("isGender = $isGender")
                saveData()
            } else {
                Utility().pesan(baseContext, "Harap pilih jenis kelamin.")
            }

        } else {
            Utility().pesan(baseContext, "Isi semua kolom yang ada diatas.")
        }
    }

    private fun saveData() {

        val name = binding.edtUsername.text.toString().trim()
        val password = binding.edtPassword.text.toString().trim()
        val handphone = binding.edtHp.text.toString().trim()
        /*saveToRoom*/
        userViewModel.insertData(context, name, password, handphone, isGender)

        runOnUiThread {
            alertDialog()
        }
    }

    private fun alertDialog() {

        val dialogBuilder = android.app.AlertDialog.Builder(this)
        dialogBuilder.setTitle("Berhasil")
        dialogBuilder.setMessage("Selamat akun Anda sudah berasil dibuat.")
            .setCancelable(false)
            .setPositiveButton("Login") { _, _ ->
                gotoLoginScreen()
            }

        val alert = dialogBuilder.create()
        alert.setTitle("Berhasil")
        alert.show()
    }

    private fun gotoLoginScreen() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    override fun onBackPressed() {
        gotoLoginScreen()
    }

}