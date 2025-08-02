package com.example.appreparto.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appreparto.databinding.ActivityProductosListBinding
import com.example.appreparto.ui.theme.ProductosAdapter
import com.example.appreparto.viewmodel.ProductosViweModel

class ProductosListActivity : AppCompatActivity() {

    private lateinit var b: ActivityProductosListBinding
    private val vm: ProductosViweModel by viewModels()

    private val adapter = ProductosAdapter(
        onDelete = { vm.delete(it.id) },
        onEdit = {startActivity(
            Intent(this, ProductosDetailActivity::class.java).putExtra("id", it.id))
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityProductosListBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.rvProductos.layoutManager = LinearLayoutManager(this)
        b.rvProductos.adapter = adapter

        vm.productos.observe(this) { list -> adapter.submitList(list) }

        b.fabAdd.setOnClickListener {
            startActivity(Intent(this, ProductosDetailActivity::class.java))
        }
    }

}
