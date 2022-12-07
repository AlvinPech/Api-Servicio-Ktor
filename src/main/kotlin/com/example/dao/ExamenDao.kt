package com.example.dao

import com.example.data.examen.Examen
import com.example.data.examen.ExamenResponse
import com.example.data.examen.ReactivoExamen
import com.example.data.reactivo.Reactivo
import com.example.data.respuesta.Respuesta

interface ExamenDao {
    suspend fun insert(
        idExamen: String,
        nombre:String,
        descripcion: String,
        idAsignatura: String,
        tiempo: Int

    ): Examen?

    suspend fun addReactivoToExam(
        idExamen: String,
        idReactivo: String,
        orden: Int
    ): ReactivoExamen?

    suspend fun getAllExamenes():List<ExamenResponse>?

    suspend fun getExamenesById(idExamen:String): ExamenResponse?

    suspend fun getExamenesByAsignaturaId(idAsignatura: String): List<ExamenResponse>?

    suspend fun getReactivosByExamenId(idExamen:String): List<Reactivo>?

    suspend fun deleteById(idExamen: String):Int

    suspend fun update(
        idExamen: String,
        nombre:String,
        descripcion: String,
        idAsignatura: String,
        tiempo: Int
    ):Int
}