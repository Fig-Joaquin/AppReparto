package com.example.appreparto.model

data class Usuario(
    val id: Int,
    val nombre: String,
    val correo: String,
    private val contrasenia: String
) {
    fun autenticar(correo: String, contrasenia: String) =
        this.correo == correo && this.contrasenia == contrasenia
}
