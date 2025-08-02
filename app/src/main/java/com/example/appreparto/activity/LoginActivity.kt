package com.example.appreparto.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.appreparto.databinding.ActivityLoginBinding
import com.example.appreparto.session.SessionManager
import com.example.appreparto.session.isAdmin
import com.example.appreparto.session.isRepartidor
import com.example.appreparto.viewmodel.LoginState
import com.example.appreparto.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val vm: LoginViewModel by viewModels()
    private lateinit var session: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        session = SessionManager(this)

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val pass  = binding.etPassword.text.toString()
            vm.login(email, pass)
        }

        vm.loginState.observe(this) { state ->
            when (state) {
                is LoginState.Success -> {
                    // Guardar email en sesión
                    session.saveEmail(state.user.correo)

                    // Determinar actividad de destino según rol
                    val destino = when {
                        isAdmin(state.user.correo)      -> AdminHomeActivity::class.java
                        isRepartidor(state.user.correo) -> RepartidorHomeActivity::class.java
                        else -> null
                    }

                    destino?.let {
                        startActivity(Intent(this, it))
                        finish()
                    } ?: Toast.makeText(this, "Usuario sin rol válido", Toast.LENGTH_SHORT).show()
                }
                is LoginState.Error -> {
                    Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
