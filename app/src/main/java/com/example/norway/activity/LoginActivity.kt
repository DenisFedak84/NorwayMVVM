package com.example.norway.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.norway.databinding.ActivityLoginBinding
import com.example.norway.lifecycle.observeEvents
import com.example.norway.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        bindUI()
    }

    private fun initBinding() {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun bindUI() {
        initListeners()
        subscribeToLiveData()
    }

    private fun subscribeToLiveData() {
        observeEvents(viewModel.eventsLiveData, ::handleEvent)
    }

    private fun handleEvent(event: LoginViewModel.Event) {
      when(event){
          is LoginViewModel.Event.ShowToken -> showToken(event.token)
      }
    }

    private fun showToken(token: String) {
    }


    private fun initListeners() {
    }

}