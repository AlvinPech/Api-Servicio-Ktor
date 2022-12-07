package com.example.dao

import com.example.data.asignatura.Asignatura
import com.example.data.profesor.Profesor

interface ProfesorDao {

    suspend fun insert(
        idProfesor: String,
        correo:String,
        nombre: String,
        apellidoPaterno: String,
        apellidoMaterno: String,
        contrasenia: String,
        esSuperUser: Boolean

    ): Profesor?

    suspend fun getAllProfesores():List<Profesor>?

    suspend fun getProfesoresById(idProfesor:String): Profesor?

    suspend fun deleteById(idProfesor: String):Int

    suspend fun update(
        idProfesor: String,
        correo:String,
        nombre: String,
        apellidoPaterno: String,
        apellidoMaterno: String,
        contrasenia: String,
        esSuperUser: Boolean
    ):Int
}