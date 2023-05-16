package com.example.norway.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.norway.viewmodel.MainViewModel
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.norway.adapters.UserAdapter
import com.example.norway.data.UserAdapterModel
import com.example.norway.databinding.ActivityMainBinding
import com.example.norway.extensions.gone
import com.example.norway.extensions.visible
import com.example.norway.extensions.visibleOrGone
import com.example.norway.lifecycle.observeEvents
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        bindUI()
    }

    private fun initBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun bindUI() {
        initListeners()
        subscribeToLiveData()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.rvUser.apply {
            userAdapter = UserAdapter()
            layoutManager = LinearLayoutManager(context)
            adapter = userAdapter
            userAdapter.onItemClicked = { userClicked(it) }
        }
    }

    private fun userClicked(user : UserAdapterModel) {
        Toast.makeText(this, "Hi, I'm ${user.name}", Toast.LENGTH_LONG).show()
    }

    private fun initListeners() {
        binding.btnGetData.setOnClickListener {
            viewModel.getNotes()
        }
        binding.btnLogin.setOnClickListener{
            startActivity(Intent(this, LoginActivity :: class.java))
        }
    }

    private fun subscribeToLiveData() {
        observeEvents(viewModel.eventsLiveData, ::handleEvent)
    }

    private fun handleEvent(event: MainViewModel.Event) {
        when (event) {
            is MainViewModel.Event.ShowNotes -> showNotes(event.notes)
            is MainViewModel.Event.ShowError -> showError(event.error)
            is MainViewModel.Event.LoadingState -> binding.progress.visibleOrGone(event.isLoading)
        }
    }

    private fun showError(error: String) {
        with (binding) {
            rvUser.gone()
            tvLabel.visible()
            tvLabel.text = error
        }
    }

    private fun showNotes(users: List<UserAdapterModel>) {
        with (binding) {
            rvUser.visible()
            tvLabel.gone()
            userAdapter.addData(users)
        }
    }
}
