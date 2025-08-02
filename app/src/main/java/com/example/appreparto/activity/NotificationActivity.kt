package com.example.appreparto.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appreparto.databinding.ActivityNotificationBinding
import com.example.appreparto.model.Notificacion
import com.example.appreparto.repository.NotificationRepository
import com.example.appreparto.viewmodel.NotificationViewModel
import com.example.appreparto.viewmodel.NotificationViewModelFactory
import java.util.*

class NotificationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotificationBinding
    private val eventId: Int by lazy { intent.getIntExtra("eventId", -1) }
    private val viewModel: NotificationViewModel by viewModels {
        NotificationViewModelFactory(eventId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ajustar padding top para status bar + display cutout
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val topInset = insets.getInsets(
                WindowInsetsCompat.Type.statusBars() or
                        WindowInsetsCompat.Type.displayCutout()
            ).top
            view.setPadding(
                view.paddingLeft,
                topInset + view.paddingTop,  // conserva el padding XML original
                view.paddingRight,
                view.paddingBottom
            )
            insets
        }

        // RecyclerView + LayoutManager
        val adapter = NotifAdapter()
        binding.rvNotificaciones.layoutManager = LinearLayoutManager(this)
        binding.rvNotificaciones.adapter = adapter

        // Observamos los cambios
        viewModel.notificaciones.observe(this) { lista ->
            adapter.submitList(lista)
        }

        // FAB inyecta nueva notificación en tiempo real
        binding.fabAdd.setOnClickListener {
            val newId = Random().nextInt(10000)
            val mensaje = "Nueva notificación $newId"
            val now = Date()
            NotificationRepository.add(
                Notificacion(newId, eventId, mensaje, now)
            )
            viewModel.refresh()
        }
    }
}
