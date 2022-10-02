package com.example.dao

import com.example.data.asignatura.Asignatura

interface ResponseDao {
    suspend fun insert(
        idPK: String,
        nombreReponse:String,
        descResponse: String,
        idFK: String
        //Agregar resto de los datos segun el modelo de la base
    ): Any?

    suspend fun getAllReponse():List<Any>?

    suspend fun getResponseById(idPK:String): Any?

    suspend fun getResponseByForeingId(idFK:String): List<Any>?

    suspend fun deleteById(idPK: String):Int

    suspend fun update(
        idPK: String,
        nombreReponse:String,
        descResponse: String,
        idFK: String
    ):Int
}