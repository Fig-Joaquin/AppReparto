package com.example.appreparto.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appreparto.databinding.ActivityProductosListBinding
import com.example.appreparto.ui.theme.ProductosAdapter
import com.example.appreparto.viewmodel.ProductosViweModel // o renombralo a ProductosViewModel si puedes

class ProductosListActivity : AppCompatActivity() {

    private lateinit var b: ActivityProductosListBinding
    private val vm: ProductosViweModel by viewModels()

    private val adapter = ProductosAdapter(
        onDelete = { vm.delete(it.id) },
        onEdit = {
            startActivity(
                Intent(
                    /* context = */ /* this can't be used directly here inside the property initializer because
                       the Activity isn't constructed yet; move adapter initialization into onCreate or use a lazy block */
                    // FIX: move adapter creation into onCreate to safely use `this`
                    Intent()
                )
            )
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityProductosListBinding.inflate(layoutInflater)
        setContentView(b.root)

        // Recreate adapter here so `this` is valid
        val safeAdapter = ProductosAdapter(
            onDelete = { vm.delete(it.id) },
            onEdit = {
                startActivity(
                    Intent(this, ProductosDetailActivity::class.java)
                        .putExtra("id", it.id)
                )
            }
        )
        b.rvProductos.layoutManager = LinearLayoutManager(this)
        b.rvProductos.adapter = safeAdapter

        vm.productos.observe(this) { list ->
            safeAdapter.submitList(list)
        }

        b.fabAdd.setOnClickListener {
            startActivity(Intent(this, ProductosDetailActivity::class.java))
        }
    }
}
