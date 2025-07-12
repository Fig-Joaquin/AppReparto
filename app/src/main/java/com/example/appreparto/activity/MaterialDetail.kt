package com.example.appreparto.activity

import com.example.appreparto.model.Material
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.appreparto.databinding.ActivityMaterialDetailBinding
import com.example.appreparto.viewmodel.MaterialViewModel

// ui/material/MaterialDetailActivity.kt
class MaterialDetailActivity : AppCompatActivity() {
    private lateinit var b: ActivityMaterialDetailBinding
    private val vm: MaterialViewModel by viewModels()     // sin Factory

    private var editing: Material? = null

    override fun onCreate(s: Bundle?) {
        super.onCreate(s)
        b = ActivityMaterialDetailBinding.inflate(layoutInflater); setContentView(b.root)

        editing = intent.getParcelableExtra("mat")
        editing?.let { fillForm(it) }

        b.btnSave.setOnClickListener {
            val m = (editing ?: Material(
                nombre = "", descripcion = "", cantidad = 0, unidad = "", valorUnitario = 0.0
            )).apply {
                nombre = b.etNombre.text.toString()
                descripcion = b.etDesc.text.toString()
                cantidad = b.etCantidad.text.toString().toIntOrNull() ?: 0
                unidad = b.etUnidad.text.toString()
                valorUnitario = b.etValor.text.toString().toDoubleOrNull() ?: 0.0
            }
            vm.save(m)
            finish()
        }
    }

    private fun fillForm(m: Material) {
        b.etNombre.setText(m.nombre)
        b.etDesc.setText(m.descripcion)
        b.etCantidad.setText(m.cantidad.toString())
        b.etUnidad.setText(m.unidad)
        b.etValor.setText(m.valorUnitario.toString())
    }
}
