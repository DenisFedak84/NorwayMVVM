package com.example.norway.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.norway.R
import com.example.norway.databinding.ActivityLoginBinding
import com.example.norway.fragments.CreateUserFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
    }

    private fun initBinding() {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun navigateToCreateUserFragment() {
       supportFragmentManager.beginTransaction().apply {
           replace(R.id.fragment_container_view, CreateUserFragment())
           addToBackStack(null)
           commit()
       }
    }
}