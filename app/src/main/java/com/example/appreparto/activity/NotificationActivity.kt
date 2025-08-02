package com.example.appreparto.activity

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appreparto.databinding.ActivityNotificationBinding
import com.example.appreparto.model.Notificacion
import com.example.appreparto.repository.NotificationRepository
import com.example.appreparto.session.SessionManager
import com.example.appreparto.session.isAdmin
import com.example.appreparto.viewmodel.NotificationViewModel
import com.example.appreparto.viewmodel.NotificationViewModelFactory
import kotlinx.coroutines.launch
import java.util.*

class NotificationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotificationBinding
    private val eventId: Int by lazy { intent.getIntExtra("eventId", -1) }

    private val viewModel: NotificationViewModel by viewModels {
        NotificationViewModelFactory(this, eventId)
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

        // Sesión y rol
        val session = SessionManager(this)
        val email = session.getEmail()

        // Solo admin puede crear notificaciones
        if (isAdmin(email)) {
            binding.fabAdd.visibility = View.VISIBLE
        } else {
            binding.fabAdd.visibility = View.GONE
        }

        val repo = NotificationRepository(this)

        binding.fabAdd.setOnClickListener {
            // Diálogo para mensaje y programación
            val input = EditText(this).apply {
                hint = "Mensaje de notificación"
            }

            AlertDialog.Builder(this)
                .setTitle("Agendar notificación")
                .setView(input)
                .setPositiveButton("Siguiente") { _, _ ->
                    val mensaje = input.text.toString().ifBlank { "Recordatorio" }
                    val nowCal = Calendar.getInstance()

                    // Fecha
                    DatePickerDialog(this, { _, year, month, dayOfMonth ->
                        val dateCal = Calendar.getInstance().apply {
                            set(year, month, dayOfMonth)
                        }

                        // Hora
                        TimePickerDialog(this, { _, hourOfDay, minute ->
                            dateCal.set(Calendar.HOUR_OF_DAY, hourOfDay)
                            dateCal.set(Calendar.MINUTE, minute)
                            dateCal.set(Calendar.SECOND, 0)
                            val scheduledTime = dateCal.timeInMillis

                            lifecycleScope.launch {
                                repo.add(
                                    Notificacion(
                                        id = 0,
                                        eventId = eventId,
                                        mensaje = mensaje,
                                        fechaHora = scheduledTime
                                    )
                                )
                                viewModel.refresh()
                            }
                        }, nowCal.get(Calendar.HOUR_OF_DAY), nowCal.get(Calendar.MINUTE), true).show()

                    }, nowCal.get(Calendar.YEAR), nowCal.get(Calendar.MONTH), nowCal.get(Calendar.DAY_OF_MONTH)).show()
                }
                .setNegativeButton("Cancelar", null)
                .show()
        }
    }
}
