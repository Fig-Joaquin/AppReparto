package com.example.appreparto.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appreparto.databinding.ActivityMaterialListBinding
import com.example.appreparto.ui.theme.MaterialAdapter
import  com.example.appreparto.viewmodel.MaterialViewModel

class MaterialListActivity : AppCompatActivity() {

    private lateinit var b: ActivityMaterialListBinding
    private val vm: MaterialViewModel by viewModels()

    private val adapter = MaterialAdapter(
        onDelete = { vm.delete(it.id) },
        onEdit   = { startActivity(
            Intent(this, MaterialDetailActivity::class.java)
                .putExtra("id", it.id)) }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMaterialListBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.rvMaterials.layoutManager = LinearLayoutManager(this)
        b.rvMaterials.adapter = adapter

        vm.materiales.observe(this) { list -> adapter.submitList(list) }

        b.fabAdd.setOnClickListener {
            startActivity(Intent(this, MaterialDetailActivity::class.java))
        }
    }
}


