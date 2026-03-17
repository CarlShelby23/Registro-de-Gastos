package com.example.registrodegastos.model

data class Gasto(
    val id: String,
    val monto: Double,
    val categoria: String,
    val fecha: String
)