package com.example.appreparto.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.appreparto.Productos
import com.example.appreparto.databinding.ActivityProductosDetailBinding
import com.example.appreparto.viewmodel.ProductosViweModel

class ProductosDetailActivity : AppCompatActivity() {
    private lateinit var b: ActivityProductosDetailBinding
    private val vm: ProductosViweModel by viewModels()     // sin Factory

    private var editing: Productos? = null

    override fun onCreate(s: Bundle?) {
        super.onCreate(s)
        b = ActivityProductosDetailBinding.inflate(layoutInflater); setContentView(b.root)

        editing = intent.getParcelableExtra("mat")
        editing?.let { fillForm(it) }

        b.btnSave.setOnClickListener {
            val p = (editing ?: Productos(
                nombre = "", descripcion = "", cantidad = 0, unidad = "", valorUnitario = 0.0
            )).apply {
                nombre = b.etNombre.text.toString()
                descripcion = b.etDesc.text.toString()
                cantidad = b.etCantidad.text.toString().toIntOrNull() ?: 0
                unidad = b.etUnidad.text.toString()
                valorUnitario = b.etValor.text.toString().toDoubleOrNull() ?: 0.0
            }
            vm.save(p)
            finish()
        }
    }

    private fun fillForm(p: Productos) {
        b.etNombre.setText(p.nombre)
        b.etDesc.setText(p.descripcion)
        b.etCantidad.setText(p.cantidad.toString())
        b.etUnidad.setText(p.unidad)
        b.etValor.setText(p.valorUnitario.toString())
    }
}