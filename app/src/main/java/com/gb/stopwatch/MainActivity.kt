package com.gb.stopwatch

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.gb.stopwatch.databinding.ActivityMainBinding
import com.gb.stopwatch.domain.*
import com.gb.stopwatch.viewmodels.MainActivityViewModel
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainActivityContract.ViewModel by lazy {
        ViewModelProvider(this)[MainActivityViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CoroutineScope(
            Dispatchers.Main + SupervisorJob()
        ).launch {
            viewModel.stopwatchListOrchestrator.ticker.collect {
                binding.textTime.text=it
            }
        }

        binding.buttonStart.setOnClickListener {
            viewModel.stopwatchListOrchestrator.start()
        }
        binding.buttonPause.setOnClickListener {
            viewModel.stopwatchListOrchestrator.pause()
        }
        binding.buttonStop.setOnClickListener {
            viewModel.stopwatchListOrchestrator.stop()
        }

    }
}
