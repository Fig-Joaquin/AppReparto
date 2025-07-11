package com.example.appreparto.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appreparto.R
import com.example.appreparto.databinding.ActivityHomeBinding



class Home : AppCompatActivity() {
    private lateinit var b: ActivityHomeBinding
    override fun onCreate(s: Bundle?) {
        super.onCreate(s)
        b = ActivityHomeBinding.inflate(layoutInflater); setContentView(b.root)

        b.btnMaterials.setOnClickListener {
            startActivity(Intent(this, MaterialListActivity::class.java))
        }
        // los otros botones pueden quedar vacíos por ahora
    }
}