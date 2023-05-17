package com.example.norway.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.norway.activity.LoginActivity
import com.example.norway.databinding.FragmentLoginBinding
import com.example.norway.lifecycle.observeEvents
import com.example.norway.viewmodel.LoginViewModel

private const val ARG_USER_NAME = "arg_user_name"

class LoginFragment : Fragment() {
    private var userName: String? = null
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

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
    }

    private fun showToken(token: String) {

    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_USER_NAME, param1)
                }
            }
    }
}