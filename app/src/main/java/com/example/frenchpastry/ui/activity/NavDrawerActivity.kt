package com.example.frenchpastry.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.frenchpastry.R
import com.example.frenchpastry.databinding.ActivityNavDrawerBinding

class NavDrawerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNavDrawerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNavDrawerBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}