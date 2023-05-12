package com.example.norway.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.norway.viewmodel.MainViewModel
import androidx.activity.viewModels
import com.example.norway.data.DataModel
import com.example.norway.databinding.ActivityMainBinding
import com.example.norway.lifecycle.observeEvents
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

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
        viewModel.getNotes()
    }

    private fun initListeners() {

    }

    private fun subscribeToLiveData() {
        observeEvents(viewModel.eventsLiveData, ::handleEvent)
    }

    private fun handleEvent(event: MainViewModel.Event) {
        when (event) {
            is MainViewModel.Event.ShowNotes -> showNotes(event.notes)
        }
    }

    private fun showNotes(notes: List<DataModel>) {
        binding.tvLabel.text = notes.size.toString()
    }

}


