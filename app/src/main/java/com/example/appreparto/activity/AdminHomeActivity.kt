package com.example.appreparto.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appreparto.databinding.ActivityAdminHomeBinding

class AdminHomeActivity : AppCompatActivity() {
    private lateinit var b: ActivityAdminHomeBinding

    override fun onCreate(s: Bundle?) {
        super.onCreate(s)
        b = ActivityAdminHomeBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.btnMaterials.setOnClickListener {
            startActivity(Intent(this, MaterialListActivity::class.java))
        }

        b.btnProducts.setOnClickListener {
            startActivity(Intent(this, ProductosListActivity::class.java))
        }


        // si quieres, aquí puedes dejar vacíos los otros botones:
        // b.btnProducts.setOnClickListener { /* ... */ }
        // b.btnEvents   .setOnClickListener { /* ... */ }
        // b.btnNotif    .setOnClickListener { /* ... */ }
    }
}
