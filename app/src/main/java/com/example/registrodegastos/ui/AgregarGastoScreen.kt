package com.example.registrodegastos.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.registrodegastos.model.Gasto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarGastoScreen(
    onCerrarClick: () -> Unit,
    onGuardarClick: (String, String) -> Unit
) {
    var cantidad by remember { mutableStateOf("") }
    var categoriaSeleccionada by remember { mutableStateOf("") }
    var expandido by remember { mutableStateOf(false) }
    val categorias = listOf("Café", "Transporte", "Cine", "Snacks", "Otros")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Agregar Gastos") },
                navigationIcon = {
                    IconButton(onClick = onCerrarClick) {
                        Icon(Icons.Default.Close, contentDescription = "Cerrar")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Sección Cantidad
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = "Cantidad", fontWeight = FontWeight.Bold)
                OutlinedTextField(
                    value = cantidad,
                    onValueChange = { cantidad = it },
                    leadingIcon = { Text("$", style = MaterialTheme.typography.titleLarge) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
            }

            // Sección Categoría
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = "Categoría", fontWeight = FontWeight.Bold)
                ExposedDropdownMenuBox(
                    expanded = expandido,
                    onExpandedChange = { expandido = !expandido }
                ) {
                    OutlinedTextField(
                        value = if (categoriaSeleccionada.isEmpty()) "Seleccione..." else categoriaSeleccionada,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandido) },
                        modifier = Modifier
                            .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                            .fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        expanded = expandido,
                        onDismissRequest = { expandido = false }
                    ) {
                        categorias.forEach { seleccion ->
                            DropdownMenuItem(
                                text = { Text(seleccion) },
                                onClick = {
                                    categoriaSeleccionada = seleccion
                                    expandido = false
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Botón Guardar
            Button(
                onClick = { onGuardarClick(cantidad, categoriaSeleccionada) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = MaterialTheme.shapes.small,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Text("GUARDAR GASTO", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AgregarGastoScreenPreview() {
    AgregarGastoScreen(
        onCerrarClick = {},
        onGuardarClick = { _, _ -> }
    )
}

@Preview(showBackground = true)
@Composable
fun HistorialScreenPreview() {
    // Lista falsa solo para ver el diseño
    val gastosFalsos = listOf(
        Gasto(id = "1", monto = 50.0, categoria = "Café", fecha = "17/03/2026"),
        Gasto(id = "2", monto = 20.0, categoria = "Transporte", fecha = "17/03/2026"),
        Gasto(id = "3", monto = 100.0, categoria = "Cine", fecha = "17/03/2026")
    )

    HistorialScreen(
        gastos = gastosFalsos,
        onBackClick = {}
    )
}