package com.example.repository.tables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ForeignKeyConstraint
import org.jetbrains.exposed.sql.Table

object ReactivoTable : Table() {
    val idreactivo:Column<String> = text("idreactivo")
    val pregunta:Column<String> = text("pregunta")
    val dificultad: Column<Int> = integer("dificultad")
    val requiereProcedimiento: Column<Boolean> = bool("requiereProcedimiento")

    override val primaryKey: PrimaryKey = PrimaryKey(idreactivo)
}