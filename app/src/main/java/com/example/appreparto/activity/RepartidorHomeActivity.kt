package com.example.appreparto.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appreparto.databinding.ActivityRepartidorHomeBinding

class RepartidorHomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRepartidorHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepartidorHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // TODO: tu lógica repartidor
    }
}
