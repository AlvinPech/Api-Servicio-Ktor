package com.example.repository.tables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ForeignKeyConstraint
import org.jetbrains.exposed.sql.Table

object AsignaturaTable : Table() {
    val idAsignatura:Column<String> = text("idasignatura")
    val nombreAsignatura:Column<String> = text("nombre")
    val descAsignatura: Column<String> = text("descripcion")
    val idProfesor: Column<String> = text("idduenio")

    override val primaryKey: PrimaryKey = PrimaryKey(idAsignatura)
}