package com.example.repository.tables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object UnidadTable : Table() {
    val idUnidad:Column<String> = text("idunidad")
    val nombreUnidad:Column<String> = text("nombre")
    val descUnidad: Column<String> = text("descripcion")
    val idAsignatura: Column<String> = text("idasignatura")

    override val primaryKey: PrimaryKey = PrimaryKey(idUnidad)
}