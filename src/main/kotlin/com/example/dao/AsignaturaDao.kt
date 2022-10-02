package com.example.dao

import com.example.data.asignatura.Asignatura


interface AsignaturaDao {
    suspend fun insert(
        idAsignatura: String,
        nombreAsignatura:String,
        descAsignatura: String,
        idProfesor: String
        //Agregar resto de los datos segun el modelo de la base
    ): Asignatura?

    suspend fun getAllAsignaturas():List<Asignatura>?

    suspend fun getAsignaturasById(idAsignatura:String): Asignatura?

    suspend fun getAsignaturasByProfesorId(idProfesor:String): List<Asignatura>?

    suspend fun deleteById(idAsignatura: String):Int

    suspend fun update(
        idAsignatura: String,
        nombreAsignatura:String,
        descAsignatura: String,
        idProfesor: String
    ):Int
}