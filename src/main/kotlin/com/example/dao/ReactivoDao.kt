package com.example.dao

import com.example.data.asignatura.Asignatura
import com.example.data.profesor.Profesor
import com.example.data.reactivo.Reactivo
import com.example.data.reactivo.ReactivoResponse
import com.example.data.reactivo.temasdereactivo
import com.example.data.respuesta.Respuesta

interface ReactivoDao {
    suspend fun insertInReactivo(
        idTema: String,
        idUnidad: String,
        idReactivo: String,
        pregunta:String,
        dificultad: Int,
        requiereProcedimiento: Boolean,

    ): Reactivo?

    /*suspend fun insertInRelation(
        idReactivo: String,
        idTema: String,
        idUnidad: String
    ): Int*/

    suspend fun getAllReactivos():List<ReactivoResponse>?

    suspend fun getReactivosById(idReactivo: String): ReactivoResponse?

    suspend fun deleteById(idProfesor: String):Int

    suspend fun getRespuestasByReactivoId(idReactivo:String): List<Respuesta>?

    /*suspend fun update(
        idProfesor: String,
        correo:String,
        nombre: String,
        apellidoPaterno: String,
        apellidoMaterno: String,
        contrasenia: String
    ):Int*/
}