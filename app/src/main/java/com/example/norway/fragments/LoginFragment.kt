package com.example.norway.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.norway.activity.LoginActivity
import com.example.norway.databinding.FragmentLoginBinding
import com.example.norway.lifecycle.observeEvents
import com.example.norway.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val ARG_USER_NAME = "arg_user_name"

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var userName: String? = null
    private lateinit var binding: FragmentLoginBinding
    val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userName = it.getString(ARG_USER_NAME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding  = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindUI()
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

    private fun initListeners() {
        binding.tvNewUser.setOnClickListener {
            (activity as LoginActivity).navigateToCreateUserFragment()
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPwd.text.toString()
            println("My user $email $password")
            viewModel.login(email, password)
        }

        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun showToken(token: String) {
        Toast.makeText(context, "Token: ${token}", Toast.LENGTH_LONG).show()
    }


    companion object {
        @JvmStatic
        fun newInstance(name: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_USER_NAME, name)
                }
            }
    }
}