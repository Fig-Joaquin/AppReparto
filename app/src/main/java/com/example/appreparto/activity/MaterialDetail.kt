package com.example.appreparto.activity

import com.example.appreparto.Material
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.appreparto.databinding.ActivityMaterialDetailBinding
import com.example.appreparto.viewmodel.MaterialViewModel


class MaterialDetailActivity : AppCompatActivity() {

    companion object { const val EXTRA_MATERIAL = "material" }

    private lateinit var b: ActivityMaterialDetailBinding
    private val vm: MaterialViewModel by viewModels()

    private var editing: Material? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMaterialDetailBinding.inflate(layoutInflater)
        setContentView(b.root)


        @Suppress("DEPRECATION")
        editing = intent.getParcelableExtra(EXTRA_MATERIAL)

        editing?.let { fillForm(it) }

        b.btnSave.setOnClickListener {
            vm.save(buildMaterialFromForm())
            finish()
        }
    }
    private fun buildMaterialFromForm(): Material {
        val base = editing ?: Material(
            nombre = "", descripcion = "",
            cantidad = 0, unidad = "", valorUnitario = 0.0
        )

        return base.copy(
            nombre        = b.etNombre.text.toString(),
            descripcion   = b.etDesc.text.toString(),
            cantidad      = b.etCantidad.text.toString().toIntOrNull() ?: 0,
            unidad        = b.etUnidad.text.toString(),
            valorUnitario = b.etValor.text.toString().toDoubleOrNull() ?: 0.0
        )
    }

    private fun fillForm(m: Material) = with(b) {
        etNombre.setText(m.nombre)
        etDesc.setText(m.descripcion)
        etCantidad.setText(m.cantidad.toString())
        etUnidad.setText(m.unidad)
        etValor.setText(m.valorUnitario.toString())
    }
}