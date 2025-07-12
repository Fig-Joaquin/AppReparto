package com.example.appreparto.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appreparto.databinding.ActivityAdminHomeBinding

class AdminHomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // inflamos el binding basado en activity_admin_home.xml
        binding = ActivityAdminHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // listeners de botones
        binding.btnMaterials.setOnClickListener {
            startActivity(Intent(this, MaterialListActivity::class.java))
        }

        binding.btnNotif.setOnClickListener {
            // Si tu NotificationActivity espera un eventId, pásalo aquí;
            // si no, simplemente lanza la pantalla genérica:
            val intent = Intent(this, NotificationActivity::class.java)
            // intent.putExtra("eventId", /* algún id válido o -1 */)
            startActivity(intent)
        }

        // binding.btnProducts.setOnClickListener { ... }
        // binding.btnEvents.setOnClickListener { ... }
        // binding.btnNotif.setOnClickListener { ... }
    }
}
