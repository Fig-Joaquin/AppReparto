package com.example.appreparto.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appreparto.databinding.ActivityRepartidorHomeBinding
import com.example.appreparto.model.Notificacion
import com.example.appreparto.session.SessionManager
import com.example.appreparto.session.isRepartidor
import com.example.appreparto.viewmodel.NotificationViewModel
import com.example.appreparto.viewmodel.NotificationViewModelFactory

class RepartidorHomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRepartidorHomeBinding
    private val eventId: Int = -1 // ver todas
    private val viewModel: NotificationViewModel by viewModels {
        NotificationViewModelFactory(this, eventId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepartidorHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Insets: evitar choque con notch / status bar si hace falta
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val top = insets.getInsets(
                WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.displayCutout()
            ).top
            // Empuja el título si necesitás, o todo el contenido
            binding.tvTitle.updatePadding(top = top)
            insets
        }

        // Verificar rol
        val session = SessionManager(this)
        val email = session.getEmail()
        if (!isRepartidor(email)) {
            binding.tvTitle.text = "Acceso inválido"
            binding.rvPreviewNotifs.visibility = View.GONE
            binding.tvPreviewTitle.visibility = View.GONE
            binding.tvEmptyPreview.apply {
                text = "No tenés permiso"
                visibility = View.VISIBLE
            }
            binding.btnVerTodas.isEnabled = false
            return
        }

        // RecyclerView preview (últimas 3)
        val adapter = NotifAdapter()
        binding.rvPreviewNotifs.layoutManager = LinearLayoutManager(this)
        binding.rvPreviewNotifs.adapter = adapter

        // Observar notificaciones
        viewModel.notificaciones.observe(this) { lista ->
            if (lista.isEmpty()) {
                binding.tvEmptyPreview.visibility = View.VISIBLE
                adapter.submitList(emptyList())
            } else {
                binding.tvEmptyPreview.visibility = View.GONE
                // Mostrar las 3 más recientes
                adapter.submitList(lista.sortedByDescending { it.fechaHora }.take(3))
            }
        }

        // Ir a pantalla completa de notificaciones
        binding.btnVerTodas.setOnClickListener {
            startActivity(Intent(this, NotificationActivity::class.java).apply {
                putExtra("eventId", eventId)
            })
        }
    }
}
