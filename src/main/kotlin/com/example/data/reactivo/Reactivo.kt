package com.example.data.reactivo

import com.example.data.respuesta.Respuesta

data class Reactivo(
    val idreactivo: String,
    val pregunta:String,
    val dificultad: Int,
    val requiereProcedimiento: Boolean,
    val correcto: Int
    )

data class temasdereactivo(
    val idreactivo: String,
    val idunidad: String,
    val idtema: String
)

data class ReactivoResponse(
    val idunidad: String,
    val unidad: String,
    val idtema: String,
    val tema: String,
    val idreactivo: String,
    val pregunta:String,
    val dificultad: Int,
    val requiereProcedimiento: Boolean,
    val correcto: Int,
    val listOfRespuestas: List<Respuesta> = listOf()
)

