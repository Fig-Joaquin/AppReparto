package com.example.appreparto.repository

import com.example.appreparto.model.Usuario

object UsuarioRepository {
    private val usuarios = listOf(
        Usuario(1, "José Admin",       "admin_jose@gmail.com",      "admin123"),
        Usuario(2, "Carlos Repartidor","repartidor_carlos@gmail.com","repartidor123"),
        Usuario(3, "Admin","admin_admin@gmail.com","admin123")

    )

    fun autenticar(correo: String, contrasenia: String): Usuario? =
        usuarios.firstOrNull { it.autenticar(correo, contrasenia) }
}
