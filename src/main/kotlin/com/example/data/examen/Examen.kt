package com.example.data.examen

import com.example.data.reactivo.Reactivo
import com.example.data.respuesta.Respuesta

data class Examen (
    val idExamen: String,
    val nombre: String,
    val descripcion: String,
    val idAsignatura: String,
    val tiempo: Int
)


data class ExamenResponse (
    val idExamen: String,
    val nombre: String,
    val descripcion: String,
    val idAsignatura: String,
    val tiempo: Int,
    val listOfReactivos: List<Reactivo> = listOf()
)

data class ReactivoExamen(
    val idExamen: String,
    val idReactivo: String,
    val orden: Int
)
