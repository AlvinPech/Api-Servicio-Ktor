package com.example.dao

import com.example.data.asignatura.Asignatura
import com.example.data.unidad.Unidad

interface UnitDao {

    suspend fun insert(
        idUnidad: String,
        nombreUnidad:String,
        descUnidad: String,
        idAsignatura: String
        //Agregar resto de los datos segun el modelo de la base
    ): Unidad?

    suspend fun getAllUnits():List<Unidad>?

    suspend fun getUnitsById(idUnidad:String): Unidad?

    suspend fun getUnitsByAsignaturaId(idAsignatura:String): List<Unidad>?

    suspend fun deleteById(idUnidad: String):Int

    suspend fun update(
        idUnidad: String,
        nombreUnidad:String,
        descUnidad: String,
        idAsignatura: String
    ):Int
}