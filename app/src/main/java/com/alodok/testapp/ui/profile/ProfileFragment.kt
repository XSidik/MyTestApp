package com.alodok.testapp.ui.profile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alodok.testapp.R
import com.alodok.testapp.databinding.FragmentProfileBinding
import com.alodok.testapp.helper.PreferenceHelper
import com.alodok.testapp.helper.PreferenceHelper.clearValues
import com.alodok.testapp.helper.PreferenceHelper.nameuser
import com.alodok.testapp.helper.Utility
import com.alodok.testapp.login.LoginActivity
import com.alodok.testapp.viewmodel.UserViewModel
import com.bumptech.glide.Glide

class ProfileFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    val PREFERENCE_NAME = "Preference"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        val prefs = PreferenceHelper.customPreference(layoutInflater.context, PREFERENCE_NAME)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        setupText(prefs)

        Glide.with(this)
            .load(R.drawable.ic_avatar)
            .circleCrop()
            .into(binding.imgUser)

        binding.btnLogout.setOnClickListener {
            logout(prefs)
        }
        return binding.root
    }

    private fun setupText(prefs: SharedPreferences) {

        userViewModel.getUserData(
            layoutInflater.context, prefs.nameuser.toString().trim()
        )!!.observe(viewLifecycleOwner, Observer {

            if (it == null) {
                binding.tvName.text = ("---")
                binding.tvHp.text = ("---")
                binding.tvGander.text = ("---")
            } else {
                binding.tvName.text = it.username
                binding.tvHp.text = it.handphone

                if (it.gender == 1) {
                    binding.tvGander.text = ("Laki - laki")
                } else if (it.gender == 0) {
                    binding.tvGander.text = ("Perempuan")
                } else {
                    binding.tvGander.text = ("Perempuan")
                }
            }
        })

    }

    private fun logout(prefs: SharedPreferences) {
        val settings =
            layoutInflater.context!!.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        settings.edit().clear().apply()
        prefs.clearValues

        val intent = Intent(layoutInflater.context, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}