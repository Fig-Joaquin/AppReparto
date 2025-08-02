package com.example.appreparto.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.appreparto.Productos
import com.example.appreparto.databinding.ActivityProductosDetailBinding
import com.example.appreparto.viewmodel.ProductosViweModel

class ProductosDetailActivity : AppCompatActivity() {

    companion object {const val EXTRA_PRODUCTO = "producto"}

    private lateinit var b: ActivityProductosDetailBinding

    private val vm: ProductosViweModel by viewModels()

    private var editing: Productos? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityProductosDetailBinding.inflate(layoutInflater)
        setContentView(b.root)

        @Suppress("DEPRECATION")
        editing = intent.getParcelableExtra(EXTRA_PRODUCTO)

        editing?.let { fillForm(it) }

        b.btnSave.setOnClickListener {
            vm.save(buildProductosFormForm())

            finish()
        }
    }

    private fun buildProductosFormForm(): Productos{
        val base = editing ?: Productos(
            nombre = "", descripcion = "", cantidad = 0, unidad = "", valorUnitario = 0.0
        )

        return base.copy(
            nombre = b.etNombre.text.toString(),
            descripcion = b.etDesc.text.toString(),
            cantidad = b.etCantidad.text.toString().toIntOrNull() ?: 0,
            valorUnitario = b.etValor.text.toString().toDoubleOrNull() ?: 0.0
        )
    }

    private fun fillForm(p: Productos) {
        b.etNombre.setText(p.nombre)
        b.etDesc.setText(p.descripcion)
        b.etCantidad.setText(p.cantidad.toString())
        b.etUnidad.setText(p.unidad)
        b.etValor.setText(p.valorUnitario.toString())
    }
}