package com.example.appreparto.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appreparto.R
import com.example.appreparto.databinding.ActivityMaterialListBinding
import com.example.appreparto.ui.theme.MaterialAdapter
import  com.example.appreparto.viewmodel.MaterialViewModel


class MaterialListActivity : AppCompatActivity() {

    private lateinit var b: ActivityMaterialListBinding
    private val vm: MaterialViewModel by viewModels()

    private val adapter = MaterialAdapter(
        onDelete = { vm.delete(it.id) },
        onEdit = {
            val intent = Intent(this, MaterialDetailActivity::class.java)
            .putExtra("id", it.id)
            startActivity(intent) }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMaterialListBinding.inflate(layoutInflater)
        setContentView(b.root)
        b.rvMaterials.layoutManager = LinearLayoutManager(this)

        b.rvMaterials.adapter = adapter

        vm.materials.observe(this) { list -> adapter.submit(list) }
        vm.loadAll()

        b.fabAdd.setOnClickListener {
            startActivity(Intent(this, MaterialDetailActivity::class.java))
        }
    }


    override fun onResume() {
        super.onResume()
        vm.loadAll()
    }
}

