package com.example.dao

import com.example.data.asignatura.Asignatura
import com.example.data.respuesta.Respuesta

interface RespuestaDao {

    suspend fun insert(
        idReactivo: String,
        orden: Int,
        idRespuesta:String,
        respuestaString: String,
        //esCorrecto: Boolean,
        idsigreactivo: String
    ): Respuesta?

    suspend fun getAllRespuestas():List<Respuesta>?

    suspend fun getRespuestasById(idRespuesta:String): Respuesta?

    suspend fun deleteById(idRespuesta: String):Int

    suspend fun update(
        idRespuesta:String,
        respuesta: String,
        //esCorrecto: Boolean,
        idsigreactivo: String
    ):Int

}