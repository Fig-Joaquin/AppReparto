package com.example.appreparto.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appreparto.databinding.ActivityAdminHomeBinding

class AdminHomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Materiales
        binding.btnMaterials.setOnClickListener {
            startActivity(Intent(this, MaterialListActivity::class.java))
        }

        // Productos
        binding.btnProducts.setOnClickListener {
            startActivity(Intent(this, ProductosListActivity::class.java))
        }

        // Evento (AÑADIDO)
        binding.btnEvents.setOnClickListener {
            startActivity(Intent(this, EventoListActivity::class.java))
        }

        // Notificaciones
        binding.btnNotif.setOnClickListener {
            startActivity(Intent(this, NotificationActivity::class.java))
        }
    }
}
