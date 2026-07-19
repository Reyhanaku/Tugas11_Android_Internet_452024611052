package com.example.tugas11

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.tugas11.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Menginisialisasi ViewModel menggunakan delegasi properti by viewModels()
    private val viewModel: OverviewViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Menginflate layout menggunakan DataBindingUtil
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengizinkan Data Binding mengamati LiveData dengan menentukan lifecycle owner
        binding.lifecycleOwner = this

        // Menghubungkan variabel viewModel di XML dengan instance ViewModel di kelas ini
        binding.viewModel = viewModel
    }
}
