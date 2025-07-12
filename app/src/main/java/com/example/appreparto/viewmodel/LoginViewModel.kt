package com.example.appreparto.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appreparto.model.Usuario
import com.example.appreparto.repository.UsuarioRepository

sealed class LoginState {
    data class Success(val user: Usuario): LoginState()
    data class Error(val message: String):   LoginState()
}

class LoginViewModel : ViewModel() {
    private val _loginState = MutableLiveData<LoginState>()
    val loginState: LiveData<LoginState> = _loginState

    fun login(correo: String, contrasenia: String) {
        val user = UsuarioRepository.autenticar(correo, contrasenia)
        if (user != null) _loginState.value = LoginState.Success(user)
        else              _loginState.value = LoginState.Error("Credenciales inválidas")
    }
}
