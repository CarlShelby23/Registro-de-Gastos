package com.example.registrodegastos.data

import android.content.Context
import com.example.registrodegastos.model.Gasto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.ArrayList

class GastoFileManager {

    companion object {
        private const val FILE_NAME = "gastos.json"
    }

    private val gson = Gson()

    fun guardarGastos(context: Context, gastos: List<Gasto>) {
        val json = gson.toJson(gastos)
        try {
            context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE).use { fos ->
                fos.write(json.toByteArray())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun leerGastos(context: Context): List<Gasto> {
        return try {
            val jsonText = context.openFileInput(FILE_NAME).bufferedReader().use { it.readText() }
            val type = object : TypeToken<ArrayList<Gasto>>() {}.type
            gson.fromJson(jsonText, type) ?: emptyList()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}