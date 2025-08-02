package com.example.appreparto.session

fun isAdmin(email: String?): Boolean = email?.startsWith("admin_") == true
fun isRepartidor(email: String?): Boolean = email?.startsWith("repartidor_") == true
