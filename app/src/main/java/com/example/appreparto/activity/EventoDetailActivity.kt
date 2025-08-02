package com.example.appreparto.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.appreparto.databinding.ActivityEventoDetailBinding
import com.example.appreparto.model.Evento
import com.example.appreparto.viewmodel.EventoViewModel

class EventoDetailActivity : AppCompatActivity() {
    private lateinit var b: ActivityEventoDetailBinding
    private val vm: EventoViewModel by viewModels()

    private var editing: Evento? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityEventoDetailBinding.inflate(layoutInflater)
        setContentView(b.root)

        val id = intent.getLongExtra("id", -1)
        editing = vm.getById(id)
        editing?.let {
            b.etTipo.setText(it.tipo)
            b.etProducto.setText(it.producto)
            b.etCantidad.setText(it.cantidad.toString())
            b.etFechaHora.setText(it.fechaHora)
            b.etUsuario.setText(it.usuario)
            b.etObservaciones.setText(it.observaciones)
            b.etStockFinal.setText(it.stockFinal.toString())
        }

        b.btnSave.setOnClickListener {
            val evento = (editing ?: Evento(
                tipo = "", producto = "", cantidad = 0,
                fechaHora = "", usuario = "", observaciones = "", stockFinal = 0
            )).apply {
                tipo = b.etTipo.text.toString()
                producto = b.etProducto.text.toString()
                cantidad = b.etCantidad.text.toString().toIntOrNull() ?: 0
                fechaHora = b.etFechaHora.text.toString()
                usuario = b.etUsuario.text.toString()
                observaciones = b.etObservaciones.text.toString()
                stockFinal = b.etStockFinal.text.toString().toIntOrNull() ?: 0
            }
            vm.save(evento)
            finish()
        }
    }
}