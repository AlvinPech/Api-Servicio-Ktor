package com.example.repository.tables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ForeignKeyConstraint
import org.jetbrains.exposed.sql.Table

object RespuestaTable : Table() {
    val idrespuesta:Column<String> = text("idrespuesta")
    val respuesta:Column<String> = text("respuesta")
    //val esCorrecto: Column<Boolean> = bool("esCorrecto")
    val idsigreactivo: Column<String> = text("idsigreactivo")

    override val primaryKey: PrimaryKey = PrimaryKey(idrespuesta)
}