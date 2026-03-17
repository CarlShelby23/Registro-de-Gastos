package com.example.registrodegastos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.registrodegastos.model.Gasto
import com.example.registrodegastos.ui.AgregarGastoScreen
import com.example.registrodegastos.ui.HistorialScreen
import com.example.registrodegastos.ui.ResumenScreen
import com.example.registrodegastos.ui.theme.RegistroDeGastosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegistroDeGastosTheme {
                AppPrincipal()
            }
        }
    }
}

@Composable
fun AppPrincipal() {
    var pantallaActual by remember { mutableStateOf("Agregar") }

    // Datos falsos para prueba
    val gastosDePrueba = listOf(
        Gasto("1", 50.0, "Café", "17/03/2026"),
        Gasto("2", 20.0, "Transporte", "17/03/2026"),
        Gasto("3", 100.0, "Cine", "17/03/2026"),
        Gasto("4", 25.0, "Snacks", "17/03/2026")
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Add, contentDescription = "Agregar") },
                    label = { Text("Agregar") },
                    selected = pantallaActual == "Agregar",
                    onClick = { pantallaActual = "Agregar" }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = "Historial") },
                    label = { Text("Historial") },
                    selected = pantallaActual == "Historial",
                    onClick = { pantallaActual = "Historial" }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Info, contentDescription = "Resumen") },
                    label = { Text("Resumen") },
                    selected = pantallaActual == "Resumen",
                    onClick = { pantallaActual = "Resumen" }
                )
            }
        }
    ) { paddingValues ->
        // Cambia el contenido
        Surface(modifier = Modifier.padding(paddingValues)) {
            when (pantallaActual) {
                "Agregar" -> AgregarGastoScreen(
                    onCerrarClick = {},
                    onGuardarClick = { _, _ -> pantallaActual = "Historial" }
                )
                "Historial" -> HistorialScreen(
                    gastos = gastosDePrueba,
                    onBackClick = { pantallaActual = "Agregar" }
                )
                "Resumen" -> ResumenScreen(
                    totalHoy = "195.00",
                    totalSemana = "680.00",
                    onBackClick = { pantallaActual = "Historial" }
                )
            }
        }
    }
}