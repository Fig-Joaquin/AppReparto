package com.example.appreparto.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appreparto.databinding.ActivityHomeBinding

class AdminHomeActivity : AppCompatActivity() {
    private lateinit var b: ActivityHomeBinding

    override fun onCreate(s: Bundle?) {
        super.onCreate(s)
        b = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.btnMaterials.setOnClickListener {
            startActivity(Intent(this, MaterialListActivity::class.java))
        }
        // si quieres, aquí puedes dejar vacíos los otros botones:
        // b.btnProducts.setOnClickListener { /* ... */ }
        // b.btnEvents   .setOnClickListener { /* ... */ }
        // b.btnNotif    .setOnClickListener { /* ... */ }
    }
}
